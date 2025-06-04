package org.lesson33;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@ToString
public class ActiveConnection {

    private int id;
    private LocalDateTime connectionTime;
    private String socket;

}
