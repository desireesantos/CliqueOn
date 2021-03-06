package br.com.infraestrutura;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.com.bean.QuizRealizada;
import br.com.service.InterfaceQuizRealizada;
import br.com.util.HibernateUtil;


public class QuizRealizadaDAO implements InterfaceQuizRealizada{
	
	
	// Método responsável por salvar os dados no BD

	
	public Boolean salvar(QuizRealizada	 quizRealizada) {   

		System.out.println("Entrou no SALVAR ENQUETE DAO !");
		Configuration configuration = new Configuration();
		configuration.configure();

		
		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		System.out.println("Nome do ADMIN:"+ quizRealizada.getCadastrarPergunta().getValue());
		System.out.println("Session Factory"+ session.getSessionFactory());
		
		Transaction tx = session.beginTransaction();
		session.save(quizRealizada);
		
		tx.commit();
		return true;
	}

	

	public List<String> listaTodos(String query) {
		return null;
	}



	public List<QuizRealizada> listarQuizRealizadas() {
		return null;
	}



	public void update(QuizRealizada novaQuiz) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.update(novaQuiz);
		
	}



	public List<QuizRealizada> excluir(QuizRealizada quiz) {
		return null;
	}



	public Integer findIDPerguntaAtual() {
	
		 Session session = HibernateUtil.getSessionFactory().openSession(); 	
		 System.out.println("Abriu a session FindQuiz ID");
		 Query query = session.createSQLQuery("select pergunta_id from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");
		 
		 return (Integer) query.uniqueResult();
	}
	
	public Integer findIDQuizAtual() {
		
		 Session session = HibernateUtil.getSessionFactory().openSession(); 	
		 System.out.println("Abriu a session FindQuiz ID");
		 Query query = session.createSQLQuery("select id_quiz from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");
		 
		 return (Integer) query.uniqueResult();
	}
	
	public String findRespostaCorretaQuizAtual(int id_pergunta) {
		
		 Session session = HibernateUtil.getSessionFactory().openSession(); 	
		 System.out.println("Abriu a session FinQuiz ID");
		 Query q = session.createSQLQuery("select resposta4 from pergunta where id = "+ id_pergunta);
		 
		 return  (String) q.uniqueResult().toString();
	}
	
	public QuizRealizada quizById(Integer id) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 QuizRealizada retorno  = (QuizRealizada) session.createCriteria(QuizRealizada.class).add(Restrictions.eq("id_quiz", id)).uniqueResult();
		 return retorno;
	}
	


}
