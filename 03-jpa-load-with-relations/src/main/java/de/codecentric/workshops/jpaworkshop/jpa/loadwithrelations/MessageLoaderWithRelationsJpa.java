package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class MessageLoaderWithRelationsJpa {
	private final EntityManager entityManager;

	public MessageLoaderWithRelationsJpa(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Message loadMessage(long id) {
		return entityManager.find(Message.class, id);
	}

	public List<Message> loadAllMessages() {
		return entityManager.createQuery("select m from Message m").getResultList();
	}

	List<Message> findAllBySender(User sender) {
		return entityManager
			.createQuery("select m from Message m where m.sender = :sender")
			.setParameter("sender", sender)
			.getResultList();
	}

	List<Message> findAllBySenderId(long id) {
		return entityManager
			.createQuery("select m from Message m where m.sender.id = :id")
			.setParameter("id", id)
			.getResultList();
	}

	public List<Message> findAllBySenderIdAndContentContains(long senderId, String content) {
		return entityManager
			.createQuery("""
				select m from Message m
				where m.sender.id = :id
				and m.content like :content
			""")
			.setParameter("id", senderId)
			.setParameter("content", "%" + content + "%")
			.getResultList();
	}

	public List<Message> findAllBySenderName(String senderName) {
		return entityManager
			.createQuery("select m from Message m where m.sender.name = :name")
			.setParameter("name", senderName)
			.getResultList();
	}

	public long countMessagesBySenderId(long senderId) {
		return entityManager
			.createQuery("select count(m) from Message m where m.sender.id = :id", Long.class)
			.setParameter("id", senderId)
			.getSingleResult();
	}

	public long countMessagesBySenderIdSql(int senderId) {
		// this is native SQL, not JPAQL, using createNativeQuery
		// use this if you want to do more complex aggregate queries.
		// if you do this more often, use spring's jdbcclient.
		return (long) entityManager
			.createNativeQuery("select count(*) from messages where sender_id = ?")
			.setParameter(1, senderId)
			.getSingleResult();
	}
}
