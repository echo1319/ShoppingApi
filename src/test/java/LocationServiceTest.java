import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.shoppingapp.apis.search.model.LocationInfo;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by eri_k on 8/27/2016.
 */
public class LocationServiceTest {


    @Test
    public void getLocationFromJson() throws IOException {
        String json = "{\n" +
                "   \"destination_addresses\" : [ \"Andrea Papandreou 35, Marousi 151 22, Greece\" ],\n" +
                "   \"origin_addresses\" : [ \"Eth. Antistaseos 48, Dafni 172 37, Greece\" ],\n" +
                "   \"rows\" : [\n" +
                "      {\n" +
                "         \"elements\" : [\n" +
                "            {\n" +
                "               \"distance\" : {\n" +
                "                  \"text\" : \"13.5 km\",\n" +
                "                  \"value\" : 13527\n" +
                "               },\n" +
                "               \"duration\" : {\n" +
                "                  \"text\" : \"29 mins\",\n" +
                "                  \"value\" : 1728\n" +
                "               },\n" +
                "               \"status\" : \"OK\"\n" +
                "            }\n" +
                "         ]\n" +
                "      }\n" +
                "   ],\n" +
                "   \"status\" : \"OK\"\n" +
                "}";


        JsonNode node = JsonLoader.fromString(json);
        LocationInfo locationInfo = new LocationInfo();
        System.out.println(node.get("rows").get(0).get("elements").get(0).get("distance").get("text"));
    }

    @Test
    public void testFormat() {

        String sql = "WITH new_values (store_id, user_id, rating) as ( values ('%s', '%s', '%s'))," +
                " upsert as " +
                " (update rating m " +
                " set store_id=nv.store_id, user_id = nv.user_id, rating= nv.rating " +
                " FROM new_values nv " +
                "WHERE m.store_id = nv.store_id AND m.user_id=nv.user_id RETURNING m.*) " +
                "INSERT INTO rating (store_id, user_id, rating) " +
                "SELECT store_id, user_id, rating " +
                "FROM new_values  WHERE NOT EXISTS " +
                "(SELECT 1 FROM upsert up" +
                " WHERE up.store_id = new_values.store_id  AND up.user_id=new_values.user_id)";

        sql = String.format(sql, "1", "2", "3");
        System.out.println(sql
        );
    }

}
