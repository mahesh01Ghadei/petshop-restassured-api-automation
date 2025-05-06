package api.payload;

import java.util.List;
import java.util.Map;

public class Pet {
	
	int id;
    Map<String, Object> category;
    String name;
    List<String> photoUrls;
    List<Map<String, Object>> tags;
    String status = "available";
	
	
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Map<String, Object> getCategory() { return category; }
    public void setCategory(Map<String, Object> category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(List<String> list) { this.photoUrls = list; }

    public List<Map<String, Object>> getTags() { return tags; }
    public void setTags(List<Map<String, Object>> tags) { this.tags = tags; }

	
	
}
