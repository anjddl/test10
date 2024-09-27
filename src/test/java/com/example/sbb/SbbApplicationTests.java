package com.example.sbb;

import com.example.sbb.Article.Article;
import com.example.sbb.Article.ArticleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;

	@Transactional
	@Test
	void testJpa() {
		Article a1 = new Article();
		a1.setSubject("sbb가 무엇인가요?");
		a1.setContent("sbb에 대해서 알고 싶습니다.");
		a1.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a1);

		Article a2 = new Article();
		a2.setSubject("스프링부트 모델 질문입니다.");
		a2.setContent("id는 자동으로 생성되나요?");
		a2.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a2);
	}
	@Test
	void testJpa2() {
		List<Article> all = this.articleRepository.findAll();
		assertEquals(2, all.size());

		Article q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa3() {
		Optional<Article> oq = this.articleRepository.findById(1);
		if(oq.isPresent()) {
			Article q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}
	@Test
	void testJpa4() {
		Article q = this.articleRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}
	@Test
	void testJpa5() {
		Article q = this.articleRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}
	@Test
	void testJpa6() {
		List<Article> qList = this.articleRepository.findBySubjectLike("sbb%");
		Article q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa7() {
		Optional<Article> oa = this.articleRepository.findById(1);
		assertTrue(oa.isPresent());
		Article a = oa.get();
		a.setSubject("수정된 제목");
		this.articleRepository.save(a);
	}
	@Test
	void testJpa8() {
		assertEquals(2, this.articleRepository.count());
		Optional<Article> oa = this.articleRepository.findById(1);
		assertTrue(oa.isPresent());
		Article a = oa.get();
		this.articleRepository.delete(a);
		assertEquals(1, this.articleRepository.count());
	}
}

