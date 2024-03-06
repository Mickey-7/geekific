package com.example.GeekificLibraries;

import com.example.GeekificLibraries.part1.BankAccount;
import com.example.GeekificLibraries.part1.CustomDeserializer;
import com.example.GeekificLibraries.part1.CustomSerializer;
import com.example.GeekificLibraries.part2.deserialize.*;
import com.example.GeekificLibraries.part2.generalimportant.*;
import com.example.GeekificLibraries.part2.includeexclude.BankAccount14;
import com.example.GeekificLibraries.part2.includeexclude.BankAccount15;
import com.example.GeekificLibraries.part2.includeexclude.BankAccount16;
import com.example.GeekificLibraries.part2.serialize.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GeekificLibrariesApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(GeekificLibrariesApplication.class, args);

	/* part1 */

		ObjectMapper objectMapper = new ObjectMapper();

		/* writeValue */
		BankAccount bankAccount = new BankAccount(123,"Geekific");
		objectMapper.writeValue(
				new File("src/bankAccount.json"), bankAccount
		);

		String asString = objectMapper.writeValueAsString(bankAccount);
		System.out.println(asString);
		/* {"id":123,"holderName":"Geekific"} */

		/* readValue */

		String jsonString = """
				{
					"id":123,
					"holderName":"Geekific"
				}
				""";
		ObjectMapper objectMapper1 = new ObjectMapper();
		BankAccount ba = objectMapper1.readValue(jsonString, BankAccount.class);
		System.out.println(ba);
		/* BankAccount(id=123, holderName=Geekific) */

		BankAccount bankAccount1 = objectMapper1.readValue(
				new File("src/bankAccount.json"), BankAccount.class
		);
		System.out.println(bankAccount1);
		/* BankAccount(id=123, holderName=Geekific) */

		BankAccount bankAccount2 = objectMapper1.readValue(
				new URL("file:src/bankAccount.json"), BankAccount.class
		);
		System.out.println(bankAccount2);
		/* BankAccount(id=123, holderName=Geekific) */

		/* readTree */
		String json = """
				{
					"id":123,
					"holderName":"Geekific"
				}
				""";
		ObjectMapper objectMapper2 = new ObjectMapper();
		JsonNode jsonRootNode = objectMapper2.readTree(json);
		System.out.println(jsonRootNode.get("holderName").asText());
		/* Geekific */

		/* parsing into an array */
		String jsonArray = """
			[
				{
					"id":123,
					"holderName":"Geekific"
				},
				{
					"id":124,
					"holderName":"Like"
				},
				{
					"id":125,
					"holderName":"Subscribe"
				}
			]
			""";
		ObjectMapper objectMapper3 = new ObjectMapper();
		objectMapper3.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		BankAccount[] accounts = objectMapper3.readValue(jsonArray, BankAccount[].class);
		for (BankAccount account : accounts) {
			System.out.println(account);
		}
		/*
			BankAccount(id=123, holderName=Geekific)
			BankAccount(id=124, holderName=Like)
			BankAccount(id=125, holderName=Subscribe)
		*/

		/* parsing into a list */
		String jsonList = """
			[
				{
					"id":123,
					"holderName":"Geekific"
				},
				{
					"id":124,
					"holderName":"Like"
				},
				{
					"id":125,
					"holderName":"Subscribe"
				}
			]
			""";
		ObjectMapper objectMapper4 = new ObjectMapper();
		List<BankAccount> bankAccountList = objectMapper4.readValue(jsonList, new TypeReference<List<BankAccount>>(){} );
		System.out.println(bankAccountList.toString());
		/* [BankAccount(id=123, holderName=Geekific), BankAccount(id=124, holderName=Like), BankAccount(id=125, holderName=Subscribe)] */

		/* parsing into a map */
		String jsonToMap = """
				{
					"id":123,
					"holderName":"Geekific"
				}
				""";
		ObjectMapper objectMapper5 = new ObjectMapper();
		Map<String, Object> objectMap = objectMapper5.readValue(jsonToMap, new TypeReference<Map<String,Object>>() {});
		System.out.println(objectMap.toString());
		/* {id=123, holderName=Geekific} */


		/* Customizations */
		String jsonString1 = """
				{
					"id":123,
					"holderName":"Geekific"
				}
				""";
		ObjectMapper objectMapper6 = new ObjectMapper();
//		JsonNode jsonRootNode1 = objectMapper6.readTree(jsonString1);
//		JsonNode jsonNode1 = jsonRootNode1.get("joinDate");
//		String joinDate = jsonNode1.asText();
		// will throw
		// Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.fasterxml.jackson.databind.JsonNode.asText()" because "jsonNode1" is null
		//	at com.example.GeekificLibraries.GeekificLibrariesApplication.main(GeekificLibrariesApplication.java:150)
		// because there is no joinDate on jsonString
		// we can use configure for this
		objectMapper6.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode jsonRootNode2 = objectMapper6.readTree(jsonString1);
		JsonNode jsonNode2 = jsonRootNode2.get("joinDate");
		String joinDate1 = (jsonNode2 != null) ? jsonNode2.asText() : null;
		System.out.println(joinDate1);
		/* null */
		objectMapper6.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		objectMapper6.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);

		/*
		Custom Serializer and Deserializer
			- useful in situations where the input or the output JSON response
			is different in structure than the Java class into which it must
			be serialized or deserialized
		*/

		ObjectMapper mapper = new ObjectMapper();

		/* Serializer */
		SimpleModule serializerModule = new SimpleModule(
				"CustomSerializer",
				new Version(1,0,0,null,null,null)
		);
		serializerModule.addSerializer(new CustomSerializer(BankAccount.class));
		mapper.registerModule(serializerModule);
		BankAccount account = new BankAccount(11,"Geekific");
		String accountJson = mapper.writeValueAsString(account);
		System.out.println(accountJson);
		/* {"holderName":"Geekific"} */

		/* Deserializer */
		String jsonCustom = """
				{
					"id":123,
					"holderName":"Geekific"
				}
				""";
		SimpleModule deserializerModule = new SimpleModule(
				"CustomDeserializer",
				new Version(1,0,0,null,null,null)
		);
		deserializerModule.addDeserializer(BankAccount.class, new CustomDeserializer(BankAccount.class));
		mapper.registerModule(deserializerModule);
		BankAccount acc = mapper.readValue(jsonCustom, BankAccount.class);
		System.out.println(acc);
		/* BankAccount(id=0, holderName=Geekific) */

		// if we add date on BankAccount class
		/*
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public class BankAccount{
			private int id;
			private String holderName;
			private Date creationDate;
		}

		default serialization is
		{
			"id":123,
			"holderName":"Geekific",
			"creationDate":"868248000000"
		}

		January 1st 1970 but not very human-readable, we can use below

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
		mapper.setDateFormat(df);
		String baAsString = mapper.writeValueAsString(acc);
		System.out.println(baAsString);

		output should be:
		{
			"id":123,
			"holderName":"Geekific",
			"creationDate":"1997-07-07 07:00 pm"
		}

		*/

		/* @JsonAnyGetter */


	/* part2 */

		/* Serializer Annotations */
		/* @JsonANyGetter - serialize a map field as standard properties */
		BankAccount3 bankAccount3 = new BankAccount3(
				123, "Geekific",
				Map.of("key1","value1","key2","value2")
		);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount3));
		/*
		{
			"id":123,
			"holderName":"Geekific",
			"key1":"value1",
			"key2":"value2"
		}
		 */

		/* make sure to put it on getter and not on the field

		private int id;
		private  String holderName;
		@JsonAnyGetter
		private Map<String, String> properties;

		{
			"id":123,
			"holderName":"Geekific",
			"properties":{
				"key2":"value2",
				"key1":"value1"
			},
			"key2":"value2",
			"key1":"value1"
		}

		private int id;
		private  String holderName;
		private Map<String, String> properties;

		{
			"id":123,
			"holderName":"Geekific",
			"properties":{
				"key2":"value2",
				"key1":"value1"
			}
		}

		*/


		/* @JsonGetter - marks a method as a getter method */
		BankAccount2 bankAccount21 = new BankAccount2(123, "Geekific");
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount21));
		/* {"id":123,"holderName":"Geekific","name":"Geekific"} */

		/* @JsonPropertyOrder(alphabetic = true) */
		BankAccount4 bankAccount4 = new BankAccount4(123,"Geekific",10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount4));
		/* {"balance":10000,"holderName":"Geekific","id":123} */

		/* @JsonPropertyOrder({"holderName", "balance", "id"}) */
		BankAccount4 bankAccount41 = new BankAccount4(123,"Geekific",10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount41));
		/* {"holderName":"Geekific","balance":10000,"id":123} */

		/* @JsonRawValue */
		BankAccount5 bankAccount5 = new BankAccount5(
				123, "Geekific",
				"""
					  {
					  	"balance":10000,
					  	"interest":0.07,
					  	"savings":30000
					  }
					"""
		);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount5));
		/*
		{"id":123,"holderName":"Geekific","json":  {
			"balance":10000,
			"interest":0.07,
			"savings":30000
		  }
		}
		*/

		/* @JsonValue - to serialize the entire instance */
		BankAccount6 bankAccount6 = new BankAccount6(123,"geekific",10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount6));
		/* "geekific" */


		/* @JsonRootName - wrap the object to serialize */
		BankAccount7 bankAccount7 = new BankAccount7(123, "Geekific", 10_000);
		ObjectMapper objectMapper7 = new ObjectMapper();
		objectMapper7.enable(SerializationFeature.WRAP_ROOT_VALUE);
		System.out.println(objectMapper7.writeValueAsString(bankAccount7));
		/*
		{
			"accountDetails":{
				"id":123,
				"holderName":"Geekific",
				"balance":10000
			}
		}
		*/

		/* @JsonSerializer - indicates a custom serializer to use */
		BankAccount8 bankAccount8 = new BankAccount8(123,"Geekific",10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount8));
		/* {"id":123,"holderName":"Geekific","balance":"9700.0"} */


		/* Deserializer Annotations */
		/* @JsonCreator - to deserialize JSON that doesn't match the object we have */
		BankAccount9 bankAccount9 = new ObjectMapper().readValue(
				new File("src/bankAccount1.json"),
				BankAccount9.class
		);
		System.out.println(bankAccount9);
		/* BankAccount9(id=123, holderName=Geekific, balance=10000) */

		// same result will be obtained using @JsonProperty
		/*
			private int id;
			@JsonProperty("name")
			private String holderName;
			private int balance;
		*/


		/* @JsonAnySetter - use a map as standard properties */
		BankAccount10 bankAccount10 = new ObjectMapper().readValue(
				new File("src/bankAccount2.json"),
				BankAccount10.class
		);
		System.out.println(bankAccount10);
		/* BankAccount10(id=123, holderName=Geekific, properties={key1=value1, key2=value2}) */

		/* @JsonSetter - use a map as standard properties */
		BankAccount11 bankAccount11 = new ObjectMapper().readValue(
				new File("src/bankAccount.json"),
				BankAccount11.class
		);
		System.out.println(bankAccount11);
		/* BankAccount11(id=123, holderName=Geekific) */

		/* @JsonDeserialize - indicates a custom deserializer to use*/
		BankAccount12 bankAccount12 = new ObjectMapper().readValue(
				new File("src/bankAccount3.json"),
				BankAccount12.class
		);
		System.out.println(bankAccount12);
		/* BankAccount12(id=123, holderName=Geekific, balance=20000) */

		/* @JsonAlias - defines alternative name for a property during deserialization */
		BankAccount13 bankAccount13 = new ObjectMapper().readValue(
				new File("src/bankAccount4.json"),
				BankAccount13.class
		);
		System.out.println(bankAccount13);
		/* BankAccount13(id=123, holderName=Geekific, balance=10000) */

		/* Include or Exclude certain attributes */
		/* @JsonIgnoreProperties - make a property or a list of properties for Jackson to ignore */
		BankAccount14 bankAccount14 = new ObjectMapper().readValue(
				new File("src/bankAccount3.json"),
				BankAccount14.class
		);
		System.out.println(bankAccount14);
		/* BankAccount14(id=123, holderName=Geekific, balance=0) */

		BankAccount14 bankAccount141 = new BankAccount14(123,"Geekific",10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount141));
		/* {"id":123,"holderName":"Geekific"} */

		BankAccount15 bankAccount15 = new ObjectMapper().readValue(
				new File("src/bankAccount5.json"),
				BankAccount15.class
		);
		System.out.println(bankAccount15);
		/* BankAccount15(id=123, holderName=Geekific, balance=10000) */

		/* @JsonIgnore - mark a single property to be ignored at the field level */
		// @JsonIgnoreProperties is at class level while @JsonIgnore is at field level

		/* @JsonInclude - excludes properties with empty, null or default values */
		BankAccount16 bankAccount16 = new BankAccount16(1, null,10_000);
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount16));
		/* {"id":1,"balance":10000} */

		/* @JsonProperty = indicates the property name in JSON */
		BankAccount17 bankAccount171 = new ObjectMapper().readValue(
				new File("src/bankAccount6.json"),
				BankAccount17.class
		);
		System.out.println(bankAccount171);
		/* BankAccount17(id=123, holderName=Geekific) */

		BankAccount17 bankAccount17 = new BankAccount17(123, "Geekific");
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount17));
		/* {"id":123,"name":"Geekific"} */

		/* @JsonFormat - specifies a format when serializing date and time values*/
		BankAccount18 bankAccount18 = new ObjectMapper().readValue(
				new File("src/bankAccount7.json"),
				BankAccount18.class
		);
		System.out.println(bankAccount18);
		/* BankAccount18(id=123, holderName=Geekific, creationDate=Mon Jul 07 08:00:00 SGT 1997) */

		/* @JsonManaged/BackReference - handle parent-child relationships and work around loops */
		List<SubAcnt> subAcnts = new ArrayList<>();
		BankAccount19 bankAccount19 = BankAccount19.builder().id(123)
				.holderName("Geekific").subAcnts(subAcnts).build();
		subAcnts.add(SubAcnt.builder().id(124).bankAccount19(bankAccount19).build());
		subAcnts.add(SubAcnt.builder().id(125).bankAccount19(bankAccount19).build());
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount19));
		/*
		{
			"id":123,
			"holderName":"Geekific",
			"subAcnts":[
				{"id":124},
				{"id":125}
			]
		}
		*/

		/* @JsonIdentityInfo - indicates the property to be used when serializing or deserializing */
		List<SubAcnt1> subAcnts1 = new ArrayList<>();
		BankAccount20 bankAccount20 = BankAccount20.builder().id(123)
				.holderName("Geekific").subAcnts1(subAcnts1).build();
		subAcnts1.add(SubAcnt1.builder().id(124).bankAccount20(bankAccount20).build());
		subAcnts1.add(SubAcnt1.builder().id(125).bankAccount20(bankAccount20).build());
		System.out.println(new ObjectMapper().writeValueAsString(bankAccount20));
		/*
		{
			"id":123,
			"holderName":"Geekific",
			"subAcnts1":[
				{
					"id":124,
					"bankAccount20":123
				},
				{
					"id":125,
					"bankAccount20":123
				}
			]
		}
		*/

		/* Disable all Annotations */
		ObjectMapper objectMapper8 = new ObjectMapper();
		objectMapper8.disable(MapperFeature.USE_ANNOTATIONS);



	}

}
