package de.codecentric.workshops.jpaworkshop.jpa.loadwithrelations;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAllBySender(User sender);

	List<Message> findAllBySenderId(long senderId);

	List<Message> findAllBySenderName(String senderName);

	List<Message> findAllBySenderIdAndContentContainsIgnoreCase(long senderId, String content);

	int countBySenderId(long senderId);

	@Query(nativeQuery = true, value = "select count(*) from messages where sender_id = ?1")
	int countBySenderIdSql(long senderId);

	@Query("select m from Message m where m.content like %?1%")
	List<Message> findWithContentSearch(String content);
}
