package com.ebook.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="Book")
public class Book{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated Book ID")
    private Integer id;

    @NotNull(message = "Please provide name of the author")
    @Column(nullable = false, length = 255)
    @ApiModelProperty(notes = "The author of the Book", required = true)
    private String author;

    @NotNull(message = "Please provide title of the book")
    @Column(nullable = false, length = 255)
    @ApiModelProperty(notes = "The title of the Book", required = true)
    private String title;

    @PastOrPresent(message = "Please enter Date not later than today")
    @Column(nullable = false)
    @ApiModelProperty(notes = "The published date of the Book", required = true)
    private Date published;

    @ApiModelProperty(notes = "The description notes", required = false)
    private String notes;
}
