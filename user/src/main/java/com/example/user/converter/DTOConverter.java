package com.example.user.converter;

import com.example.user.model.User;
import com.example.shoppingclient.dtoUser.UserDTO;

public class DTOConverter {

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEndereco(user.getEndereco());
        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelefone(user.getTelefone());
        userDTO.setKey(user.getKey());
        userDTO.setDataCadastro(user.getDataCadastro());
        return userDTO;
    }

}
