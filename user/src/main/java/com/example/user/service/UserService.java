package com.example.user.service;


import com.example.shoppingclient.errorDTO.UserNotFoundException;
import com.example.user.converter.DTOConverter;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.shoppingclient.dtoUser.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> queryByName(final String name) {
        return userRepository.queryByNomeLike(name).stream()
                .map(DTOConverter::convert).collect(Collectors.toList());
    }


    public UserDTO findByCpf(final String cpf, String key) {
        return userRepository.findByCpfAndKey(cpf, key).map(u-> DTOConverter.convert(u))
                .orElseThrow(() -> new UserNotFoundException("Not found"));
    }


    public void delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found"));
        userRepository.delete(user);
    }


    public UserDTO save(UserDTO dto) {
        dto.setDataCadastro(new Date());
        dto.setKey(UUID.randomUUID().toString());
        User user = userRepository.save(User.convert(dto));
        return DTOConverter.convert(user);
    }


    public UserDTO findById(long id) {
        return userRepository.findById(id).map(DTOConverter::convert)
                .orElseThrow(() -> new UserNotFoundException("Not found"));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

}
