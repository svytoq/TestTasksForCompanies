package org.example.dto;

import java.util.List;

public record NewTopic(String name, List<NewMessage> newMessageList) {
}
