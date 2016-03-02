package br.com.fiap.entity;

import javax.faces.bean.ManagedBean;

public enum StatusEnum { 
	APROVADO {
		@Override
		public String toString() {
			return "Aprovado";
		}
	},
	REPROVADO {
		@Override
		public String toString() {
			return "Reprovado";
		}
	},
	PENDENTE {
		@Override
		public String toString() {
			return "Pendente";
		}
	}

}
