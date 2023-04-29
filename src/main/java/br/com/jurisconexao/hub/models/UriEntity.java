package br.com.jurisconexao.hub.models;

import jakarta.persistence.*;

@Entity
@Table(name = "uris")
public class UriEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String uri;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public UriEntity(Long id, String path, String uri) {
		super();
		this.id = id;
		this.path = path;
		this.uri = uri;
	}

	public UriEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

   
}
