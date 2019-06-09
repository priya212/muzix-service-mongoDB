package com.stackroute.muzixservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Muzix {
    @Id
    private int muzixId;
    private String muzixName;
    private String comments;
}
