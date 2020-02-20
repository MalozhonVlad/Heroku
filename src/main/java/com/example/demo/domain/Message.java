package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Message cannot be empty")
    @Length(max = 2048, message = "Message to long (more then 2kB)")
    private String text;

    @NotBlank(message = "Tag cannot be empty")
    @Length(max = 255, message = "Tag to long (more then 255 symbols)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

//    @Lob
    @Basic(fetch=FetchType.EAGER)
    private Byte[] bytes;

    @Transient
    private String filename;

}
