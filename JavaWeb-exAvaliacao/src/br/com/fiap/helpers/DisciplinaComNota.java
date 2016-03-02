package br.com.fiap.helpers;

import br.com.fiap.entity.BaseEntity;
import br.com.fiap.entity.Nota;
import br.com.fiap.entity.StatusEnum;
import br.com.fiap.entity.TipoNotaEnum;

public class DisciplinaComNota extends BaseEntity{


	private String nome;
	private Nota notaP1;
	private Nota notaAtvd;
	private Nota notaP2;
	private Double media;
	private StatusEnum status;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Nota getNotaP1() {
		return notaP1;
	}
	public void setNotaP1(Nota notaP1) {
		this.notaP1 = notaP1;
	}
	public Nota getNotaAtvd() {
		return notaAtvd;
	}
	public void setNotaAtvd(Nota notaAtvd) {
		this.notaAtvd = notaAtvd;
	}
	public Nota getNotaP2() {
		return notaP2;
	}
	public void setNotaP2(Nota notaP2) {
		this.notaP2 = notaP2;
	}
	public Double getMedia() {
		Double nota1temp = null;
		Double nota2temp = null;
		Double notaAtvdTemp = null;
		if(notaP1.getValor() == null){ nota1temp = 0d;}else{nota1temp = notaP1.getValor();} 
		if(notaP2.getValor() == null){nota2temp = 0d;} else{nota2temp = notaP2.getValor();} 
		if(notaAtvd.getValor() == null ) {notaAtvdTemp = 0d;}else{notaAtvdTemp = notaAtvd.getValor();} 

		media =  (nota1temp * TipoNotaEnum.PROJETO_1.getFator()) + 
				(nota2temp * TipoNotaEnum.PROJETO_2.getFator()) + 
				(notaAtvdTemp * TipoNotaEnum.ATIVIDADE_PRATICA.getFator());
		setMedia(media);




		return  media;





	}
	public void setMedia(Double media) {
		this.media = media;
	}
	public StatusEnum getStatus() {

		if (media != null) {


			if(media >= 7){
				setStatus(StatusEnum.APROVADO);
			}else if(media < 7 && (notaP1.getValor() == null || notaP2.getValor() == null || notaAtvd.getValor() == null)  ){
				setStatus(StatusEnum.PENDENTE);
			}else if (notaP1.getValor() == null && notaP2.getValor() == null && notaAtvd.getValor() == null){
				setStatus(StatusEnum.PENDENTE);
			}
			else{
				setStatus(StatusEnum.REPROVADO);
			}
			return status;
		}else{
			setStatus(StatusEnum.PENDENTE);
		}
		System.out.println(media);
		System.out.println(status);
		return status;

	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
}
