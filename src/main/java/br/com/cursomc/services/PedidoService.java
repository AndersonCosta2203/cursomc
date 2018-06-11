package br.com.cursomc.services;

import br.com.cursomc.domain.*;
import br.com.cursomc.domain.enums.EstadoPagamento;
import br.com.cursomc.repositories.ItemPedidoRepository;
import br.com.cursomc.repositories.PagamentoRepository;
import br.com.cursomc.repositories.PedidoRepository;
import br.com.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired // Injeção de dependência
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id) {
        // A partir da versão 2.x.x do Spring, o método findById substitui o método findOne
        // A finalidade de uso classe Optional é para null, quando o resultado não é encontrado
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+id+", Tipo: "+Pedido.class.getName()));
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional // usar @transaction no insert
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj); // O pagamento tem que conhecer o pedido dele

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pag = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pag, obj.getInstante());
        }

        obj = pedidoRepository.save(obj);

        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido itemPedido : obj.getItens()) {
            itemPedido.setDesconto(0.00);
            itemPedido.setProduto(produtoService.find(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());

        emailService.senderOrderConfirmationEmail(obj);

        return obj;
    }
}
