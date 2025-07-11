package com.events.products.business.service;

import com.events.products.business.exception.UserNotFoundException;
import com.events.products.data.dto.UserDto;
import com.events.products.data.entity.UserEntity;
import com.events.products.data.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto addUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity entity = objectMapper.convertValue(userDto, UserEntity.class);

        UserEntity savedEntity = userRepository.save(entity);

        return objectMapper.convertValue(savedEntity, UserDto.class);
    }

    public UserDto getUser(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return objectMapper.convertValue(entity, UserDto.class);
    }

    public UserDto patchUser(Long id, UserDto userDto) throws Exception {
        UserEntity existingEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        JsonNode existingNode = objectMapper.valueToTree(existingEntity);
        JsonNode updateNode = objectMapper.valueToTree(userDto);

        JsonNode merged = merge(existingNode, updateNode);

        UserEntity updatedEntity = objectMapper.treeToValue(merged, UserEntity.class);
        updatedEntity.setId(id);

        UserEntity savedEntity = userRepository.save(updatedEntity);

        return objectMapper.convertValue(savedEntity, UserDto.class);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    private JsonNode merge(JsonNode mainNode, JsonNode updateNode) {
        if (mainNode instanceof ObjectNode && updateNode instanceof ObjectNode) {
            ObjectNode mainObjectNode = (ObjectNode) mainNode;
            ObjectNode updateObjectNode = (ObjectNode) updateNode;

            updateObjectNode.fieldNames().forEachRemaining(fieldName -> {
                JsonNode updatedValue = updateObjectNode.get(fieldName);
                if (updatedValue.isNull()) {
                    mainObjectNode.set(fieldName, null);
                } else {
                    JsonNode existingValue = mainObjectNode.get(fieldName);
                    if (existingValue != null && existingValue.isObject()) {
                        JsonNode mergedNode = merge(existingValue, updatedValue);
                        mainObjectNode.set(fieldName, mergedNode);
                    } else {
                        mainObjectNode.set(fieldName, updatedValue);
                    }
                }
            });
            return mainObjectNode;
        }
        return updateNode;
    }
}
