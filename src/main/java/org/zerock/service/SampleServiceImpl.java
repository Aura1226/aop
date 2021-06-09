package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.SampleMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
@Transactional
public class SampleServiceImpl implements SampleService{

	
	private final SampleMapper mapper;
	
	//@Transactional //트랜젝션은 3곳에 넣을 수 있다? 1.메서드 2. 클래스 3. 인터페이스 => 우선 순위도 1,2,3 순 이다
	@Override
	public String doA() {
		log.info("doA...........");
		
		String str = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세. 남산 위에 저 소나무 철갑을 두른 듯. 바람 서리 불변함은 우리 기상일세. 가을 하늘 공활한데 높고 그름 없이...";
		
		mapper.insert1(str);
		mapper.insert2(str);
		
		
		return "doA";
	}

	@Override
	public String doB() {
//		log.info("doB..............");
		mapper.insert3("AAA", "BBB", "CCC");
		
		log.info("-----------------------------");
		log.info("-----------------------------");
		log.info(mapper.getLast());
		log.info("-----------------------------");
		
		return "doB";
	}

	@Override
	public String doC() {
		log.info("doC..............");
		return "doC";
	}

}
