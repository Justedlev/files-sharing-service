package com.justedlev.service.filesharing.storage.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class FileInfo {

    private String name;
    private long size;

}
