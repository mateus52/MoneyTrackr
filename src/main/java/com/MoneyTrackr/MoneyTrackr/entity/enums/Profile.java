package com.MoneyTrackr.MoneyTrackr.entity.enums;

public enum Profile {
	
	ADMIN(1L, "ROLE_ADMIN"),
	CLIENTE(2L, "ROLE_CLIENT");
	
	private Long cod;
	private String description;
	
	private Profile(Long cod, String descricao) {
		this.cod = cod;
		this.description = descricao;
	}
	
	public Long getCod() {
		return cod;
	}
	
	public String getDesc () {
		return description;
	}
	
	public static Profile toEnum(Long cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Profile x : Profile.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid: " + cod);
	}

}
