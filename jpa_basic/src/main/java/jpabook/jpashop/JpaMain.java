package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // code

        try{

            Order order = new Order();
            em.persist(order);

            OrderItem orderItem1 = new OrderItem();
            OrderItem orderItem2 = new OrderItem();
            em.persist(orderItem1);
            em.persist(orderItem2);

            order.addOrderItem(orderItem1);
            order.addOrderItem(orderItem2);
            System.out.println("order = " + order.getOrderItems());

            em.flush();
            em.clear();

            System.out.println("======================================");
            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("======================================");
            System.out.println("findOrder = " + findOrder.getOrderItems());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }


}
