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
public class NewUserDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
		private String userName;
		
		@NotEmpty(message="Preenchimento obrigatório")
		@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
		private String email;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String document;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String password;
		
		@NotEmpty(message="Preenchimento obrigatório")
		private String phone;
}
