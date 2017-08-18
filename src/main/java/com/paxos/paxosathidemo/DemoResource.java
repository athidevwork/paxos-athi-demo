package com.paxos.paxosathidemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/")
@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class DemoResource {
	Logger logger = Logger.getLogger("DemoResource");
	HashMap<String, String> hashMap = new HashMap<String, String>();
	static TreeMap<String, Double> pricesMap = new TreeMap<String, Double>();
	
	static {
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/prices.txt"))) {
			
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				pricesMap.put(lineArr[0], Double.parseDouble(lineArr[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	public Response home() {
		return Response.ok().entity("Reached Demo Resource").build();
	}
	
	@GET
	@Path("/messages")
	public Response listAllMessages() {
		List<String> messagesList = new ArrayList<String>(hashMap.values());
		logger.info(messagesList.toString());
		return Response.ok().type(MediaType.TEXT_PLAIN).entity(messagesList).build();
	}
	
	@GET
	@Path("/findpair/{amount}")
	public String findPair(@PathParam("amount") double totalAmount) {
		logger.info("Amount = " + totalAmount + ", findPair computed from map : " + pricesMap);
		
		StringBuilder gifts = new StringBuilder();
		double sumAmount = 0.0;
		
        for (String key : pricesMap.keySet()){
        	//logger.info("loop for " + key + " = " + pricesMap.get(key));
        	for (String key1 : pricesMap.keySet()) {
            	sumAmount = pricesMap.get(key) + pricesMap.get(key1);
            	//logger.info("Sum Amount = " + sumAmount);
        		if (sumAmount <= totalAmount) {
        			gifts.append(key).append(" ").append(pricesMap.get(key)).append(", ")
        			.append(key1).append(" ").append(pricesMap.get(key1)).append("\n");      			
        			//logger.info("Adding Pair " + key + " = " + pricesMap.get(key) + ", " + key1+ " = " + pricesMap.get(key1));
        		}
        		else {
        			gifts.append(key).append(" ").append(pricesMap.get(key)).append(", ")
        			.append(key1).append(" ").append(pricesMap.get(key1)).append(" - ").append("Not Possible\n");
        		}
        	}
        }

        logger.info("\ngift pairs = " + gifts);
		return gifts.toString();
	}
	
	@GET
	@Path("/maxpairs/{amount}")
	public String findMaxPairs(@PathParam("amount") double totalAmount) {
		logger.info("Amount = " + totalAmount + ", findPair computed from map : " + pricesMap);
		
		StringBuilder maxGifts = new StringBuilder();
		double maxAmount = 0.0;
		
        for (String key : pricesMap.keySet()){
        	//logger.info("loop for " + key + " = " + pricesMap.get(key));
        	if (pricesMap.get(key) <= totalAmount) {
        		maxGifts.append(key).append(" ").append(pricesMap.get(key)).append(", ");
        	}
        	else {
        		maxGifts.append(key).append(" ").append(pricesMap.get(key)).append(" - Not possible").append(", ");
        	}
        	maxAmount = pricesMap.get(key);
        	for (String key1 : pricesMap.keySet()) {
            	maxAmount += pricesMap.get(key1);
    			//logger.info("Max Amount = " + maxAmount);
        		if (maxAmount <= totalAmount) {
        			//logger.info("Adding " + key1+ " = " + pricesMap.get(key1));
        			maxGifts.append(key1).append(" ").append(pricesMap.get(key1)).append(", ");
        			//logger.info("max gift = " + maxGifts);
        		}
        		else {
        			maxAmount -= pricesMap.get(key1);
        		}
        	}
        	maxGifts.append("total=").append(maxAmount).append("\n");
        }

        logger.info("\nmax gift for amount = " + totalAmount + "\n" + maxGifts);
		return maxGifts.toString();
	}
	
	@GET
	@Path("/replaceX/{pattern}")
	public String replaceX(@PathParam("pattern") String templatePattern) {
		StringBuilder combinations = new StringBuilder();
		LinkedList<String> combinationsList = generate(templatePattern);

		for(String combination : combinationsList) {
			//logger.info(combination);
			combinations.append(combination).append("\n");
		}
		
		return combinations.toString();
	}
	
	static LinkedList<String> generate(String pattern) {
		LinkedList<String> strings = new LinkedList<String>();
		int pos = pattern.indexOf("X");
		if(pos < 0) {
			strings.add(pattern);
		} else {
			String zeroPattern = pattern.substring(0, pos) + "0" + pattern.substring(pos+1);
			String onePattern = pattern.substring(0, pos) + "1" + pattern.substring(pos+1);
			strings.addAll(generate(zeroPattern));
			strings.addAll(generate(onePattern));
		}
		return strings;
	}
	
	@GET
	@Path("/messages/{hash}")
	public Response getHash(@PathParam("hash") String hash) {
        logger.info("Fetching hash " + hash);
        String string4Hash = hashMap.get(hash);
        logger.info("Hash String = " + string4Hash);
        if (string4Hash == null)
        	return Response.status(404).entity(hash + " for message not found").build();
        return Response.ok(string4Hash).build();
    }
	
	@POST
	@Path("/messages")
	public String createMessageHash (String message) {
		logger.info("Creating hash for message " + message);
  
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(message.getBytes());
		String hashString = bytesToHex(md.digest());
		hashMap.put(hashString, message);
		logger.info("Hash String : " + hashString + ", Map count = " + hashMap.size());
        return hashString;
    }
	
	private String bytesToHex(byte[] bytes) {
	    StringBuffer result = new StringBuffer();
	    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    return result.toString();
	}
	
	
}
