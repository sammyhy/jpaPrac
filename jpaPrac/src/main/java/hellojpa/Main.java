package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPrac");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Team team = new Team();
			team.setName("Arsenal");
			em.persist(team);
			
			Member member = new Member();
			member.setName("Nicolas Pepe");
			member.setAge(26);
			member.setTeam(team); // 연관관계의 주인에 값 설정
			team.getMembers().add(member);
			em.persist(member);
			
			em.flush();
			em.clear();
			
//			//select
//			Member findMember = em.find(Member.class, member.getId());
//			//연관관계가 없다
//			Team findTeam = findMember.getTeam();
//			
//			findTeam.getName();
//			
//			List<Member> members = findTeam.getMembers();
//			for(Member member1 : members) {
//				System.out.println("member1 : "+ member1);
//			}
			
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
		} finally {
			em.close();			
		}
		emf.close();
		
	}
}
