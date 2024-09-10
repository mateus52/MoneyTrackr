package com.MoneyTrackr.MoneyTrackr.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=10, max=10, message="O tamanho deve ter 10 caracteres")
		private String initialData;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=10, max=10, message="O tamanho deve ter 10 caracteres")
		private String finalData;
		
}
