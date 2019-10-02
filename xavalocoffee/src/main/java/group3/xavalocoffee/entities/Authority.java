package group3.xavalocoffee.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_authorities")
public class Authority {
    @Id
    @Column(name = "authority_id")
    private int id;

    @Column(name = "authority_name")
    private String authorityName;

    public Authority() {
    }

    public Authority(int id, String authorityName) {
        this.id = id;
        this.authorityName = authorityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
