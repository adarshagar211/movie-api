package com.backbase.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) 
@Entity
@Table(name="MOVIE_TABLE")
public class Movie {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String yearNomination;
	private String category;
	private String nominee;
	private String addInfo;
	private String won;
	
	@Column(precision = 5 , scale=2 ,columnDefinition = "decimal default 0.00")
	@Builder.Default
	private BigDecimal ratings = BigDecimal.ZERO;
	
	@Column(columnDefinition = "integer default 0")
	@Builder.Default
	private Integer totalVotes= 0;
	
	@Builder.Default
	private Double boxOfficeCollection = 0.00;
}
