package br.com.fiap.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.fiap.entity.Aluno;

@ManagedBean
@ViewScoped
//NÃO USAR
public class AlunoBean {

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	Aluno aluno;

}
