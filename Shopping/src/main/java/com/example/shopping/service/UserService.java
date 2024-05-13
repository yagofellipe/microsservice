package com.example.shopping.service;


import com.example.shoppingclient.dtoUser.UserDTO;
import com.example.shoppingclient.errorDTO.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8081")
    private String userApiURL;
    public UserDTO getUserByCpf(String cpf, String key){
        try {
            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userApiURL + "/user/cpf" + cpf);
            builder.queryParam("key", key);

            ResponseEntity<UserDTO> response = restTemplate.getForEntity(builder.toUriString(), UserDTO.class);
            return response.getBody();
        }catch (HttpClientErrorException e){
            throw new UserNotFoundException(e.getMessage());
        }
    }

}
