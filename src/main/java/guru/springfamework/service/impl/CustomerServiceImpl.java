package guru.springfamework.service.impl;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.mapper.CustomerMapper;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
                    return customerDTO;
                })
                .toList();
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customers/" + id);
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var customer = customerMapper.customerDtoToCustomer(customerDTO);
        var savedCustomer = customerRepository.save(customer);
        var savedCustomerDto = customerMapper.customerToCustomerDTO(savedCustomer);
        savedCustomerDto.setCustomerUrl("/api/v1/customers/" + savedCustomer.getId());

        return savedCustomerDto;
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        var customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        var updatedCustomer = customerRepository.save(customer);
        var updatedCustomerDto = customerMapper.customerToCustomerDTO(updatedCustomer);
        updatedCustomerDto.setCustomerUrl("/api/v1/customers/" + id);

        return updatedCustomerDto;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
