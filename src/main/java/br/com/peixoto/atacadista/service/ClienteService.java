package br.com.peixoto.atacadista.service;

import br.com.peixoto.atacadista.domain.Cidade;
import br.com.peixoto.atacadista.domain.Cliente;
import br.com.peixoto.atacadista.domain.Endereco;
import br.com.peixoto.atacadista.dto.ClienteRequestDTO;
import br.com.peixoto.atacadista.dto.ClienteResponseDTO;
import br.com.peixoto.atacadista.dto.EnderecoRequestDTO;
import br.com.peixoto.atacadista.exception.AbstractMessageErrorCode;
import br.com.peixoto.atacadista.exception.BadRequestException;
import br.com.peixoto.atacadista.exception.ErrorMessage;
import br.com.peixoto.atacadista.jpamodel.CrudService;
import br.com.peixoto.atacadista.jpamodel.FindService;
import br.com.peixoto.atacadista.jpamodel.MergeService;
import br.com.peixoto.atacadista.repository.CidadeRepository;
import br.com.peixoto.atacadista.repository.ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class ClienteService implements
        CrudService<ClienteRequestDTO, ClienteResponseDTO>,
        FindService<ClienteRequestDTO, ClienteResponseDTO>,
        MergeService<Cliente, ClienteResponseDTO> {

    private final ClienteRepository clienteRepository;

    private final CidadeRepository cidadeRepository;

    private final ModelMapper modelMapper;

    @Override
    public ClienteResponseDTO save(ClienteRequestDTO requestBody) {

        final Cidade cidade = cidadeRepository.findById(Long.parseLong(requestBody.getEndereco().getCidade().getId()))
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CIDADE_NAO_ENCONTRADA, requestBody.getEndereco().getCidade().getId())));

        final EnderecoRequestDTO enderecoRequestDTO = requestBody.getEndereco();

        final Endereco endereco = new Endereco();
        modelMapper.map(enderecoRequestDTO, endereco);

        endereco.setCidade(cidade);

        final var cliente = new Cliente();
        modelMapper.map(requestBody, cliente);

        cliente.setEndereco(endereco);

        final var response = new ClienteResponseDTO();

        modelMapper.map(clienteRepository.save(cliente), response);

        return response;
    }

    @Override
    @Transactional
    public ClienteResponseDTO merge(final Cliente cliente) {

        final var response = new ClienteResponseDTO();
        modelMapper.map(clienteRepository.save(cliente), response);

        return response;
    }

    @Override
    public Cliente getObjectAtual(Long idCliente, Object requestBody) {

        final ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();
        modelMapper.map(requestBody, clienteRequestDTO);

        final Cidade cidade = cidadeRepository.findById(Long.parseLong(clienteRequestDTO.getEndereco().getCidade().getId()))
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CIDADE_NAO_ENCONTRADA, clienteRequestDTO.getEndereco().getCidade().getId())));

        final EnderecoRequestDTO enderecoRequestDTO = clienteRequestDTO.getEndereco();

        final Endereco endereco = new Endereco();
        modelMapper.map(enderecoRequestDTO, endereco);

        endereco.setCidade(cidade);

        final Cliente clienteAtual = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO, idCliente)));

        clienteAtual.getEndereco().setCidade(cidade);

        modelMapper.map(clienteRequestDTO, clienteAtual);

        return clienteAtual;

    }

     @Override
    public List<ClienteResponseDTO> findAll() {

        final List<Cliente> clienteList = clienteRepository.findAll();

        final List<ClienteResponseDTO> clienteResponseList = new ArrayList<>();

        clienteList.forEach(cliente -> {

            final var clienteResponse = new ClienteResponseDTO();
            modelMapper.map(cliente, clienteResponse);

            clienteResponseList.add(clienteResponse);

        });
        return clienteResponseList;
    }

    @Override
    public ClienteResponseDTO findById(final Long idCliente) {

        final Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO, idCliente)));

        final var clienteResponse = new ClienteResponseDTO();
        modelMapper.map(cliente, clienteResponse);

        return clienteResponse;
    }

    public ClienteResponseDTO findByCpf(final String cpf) {

        final Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO)));

        final var clienteResponse = new ClienteResponseDTO();
        modelMapper.map(cliente, clienteResponse);

        return clienteResponse;
    }

    public List<ClienteResponseDTO> findByUF(final Long estadoId) {

        final List<Cliente> clienteList = clienteRepository.findByEnderecoCidadeEstadoId(estadoId)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO)));

        final List<ClienteResponseDTO> clienteResponseList = new ArrayList<>();

        clienteList.forEach(cliente -> {

            final var clienteResponse = new ClienteResponseDTO();
            modelMapper.map(cliente, clienteResponse);

            clienteResponseList.add(clienteResponse);

        });
        return clienteResponseList;

    }

    @Override
    @Transactional
    public ClienteResponseDTO update(final Long objectId, final ClienteRequestDTO requestBody) {

        final Cliente clienteAtual = getObjectAtual(objectId, requestBody);

        return merge(clienteAtual);

    }

    @Override
    @Transactional
    public void delete(final Long idCliente) {

        final Cliente clienteAtual = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new BadRequestException(
                        new ErrorMessage(AbstractMessageErrorCode.CLIENTE_NAO_ENCONTRADO, idCliente)));

        clienteRepository.delete(clienteAtual);

    }
}