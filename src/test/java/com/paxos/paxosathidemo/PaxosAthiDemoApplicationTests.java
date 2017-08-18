package com.paxos.paxosathidemo;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaxosAthiDemoApplicationTests {

	static DemoResource demo = null;

	@BeforeClass
	public static void setup() {
		demo = new DemoResource();
	}
	
	@Test
	public void testReplaceX() {
		demo.replaceX("X0");
		demo.replaceX("10X10X0");
	}
	
	@Test
	public void testFindPair() {
		demo.findPair(2500);
		demo.findMaxPairs(2500);
	}
	
	@Test
	public void startTest() {
		testCreateHash();
		testGetHash();
		testGetMessages();
	}

	public void testCreateHash() {
		assertEquals("2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae", demo.createMessageHash("foo"));
		assertEquals("fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9",demo.createMessageHash("bar"));
		assertEquals("c3ab8ff13720e8ad9047dd39466b3c8974e592c2fa383d4a3960714caef0c4f2",demo.createMessageHash("foobar"));
	}
	
	public void testGetHash() {
		Response resp = demo.getHash("2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae");		
		assertEquals("foo", (String) resp.getEntity());
		resp = demo.getHash("2c26b46b68ffc68ff99b453c1d3041341");
		assertEquals("2c26b46b68ffc68ff99b453c1d3041341 for message not found", (String) resp.getEntity());
	}
	
	public void testGetMessages() {
		demo.listAllMessages();
	}
}
