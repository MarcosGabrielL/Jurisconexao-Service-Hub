package br.com.jurisconexao.hub.utils;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import br.com.jurisconexao.hub.models.UriEntity;




public class Utils {


	public List<LinkedHashMap<String, String>> converter(List<UriEntity> originalList) {
		List<LinkedHashMap<String, String>> resultList = new ArrayList<>();

		for (UriEntity entity : originalList) {
		    LinkedHashMap<String, String> map = new LinkedHashMap<>();
		    map.put("id", entity.getId().toString());
		    map.put("uri", entity.getUri().toString());
		    map.put("path", entity.getPath().toString());
		    resultList.add(map);
		}

		return resultList;
	}
	
	
}