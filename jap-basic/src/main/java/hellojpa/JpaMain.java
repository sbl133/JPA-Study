package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // code

        try {
            Team team1 = new Team();
            team1.setName("팀1");
            em.persist(team1);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team1);
            em.persist(member2);

            Team team2 = new Team();
            team2.setName("팀2");
            em.persist(team2);

            Member member3 = new Member();
            member3.setUsername("관리자3");
            member3.setTeam(team2);
            em.persist(member3);

            Member member4 = new Member();
            member4.setUsername("관리자4");
            member4.setTeam(team2);
            em.persist(member4);

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();
            List<Team> teams = em.createQuery("select t from Team t", Team.class)
                    .getResultList();

            for (Member member : members) {
                System.out.println("member = " + member);
                System.out.println(member.getTeam());
                System.out.println(member.getTeam().getName());
            }

//            for (Team team : teams) {
//                System.out.println("team = " + team);
//                System.out.println(team.getMembers());
//                System.out.println(team.getMembers().get(0).getUsername());
//            }


//            String query = "select distinct t from Team t join fetch t.members";
//
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//            for (Team team : result) {
//                for (Member member : team.getMembers()) {
//                    System.out.println(member.getUsername());
//                }
//            }
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//            for (Team team : result) {
//                for (Member member : team.getMembers()) {
//                    System.out.println(member.getTeam().getName());
//                }
//            }

//            String query = "select m from Member m join fetch m.team";
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//            for (Member member : result) {
//                System.out.println(member.getTeam().getName());
//            }
//            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//            for (Member member : result) {
//                for (Member teamMember : member.getTeam().getMembers()) {
//                    System.out.println(teamMember.getUsername());
//                }
//            }


            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }


}
