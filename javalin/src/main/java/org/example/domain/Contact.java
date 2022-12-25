package org.example.domain;

import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import org.example.domain.query.QContact;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

/**
 * @author andreiserov
 */
@Entity
public class Contact extends Model {

    public static final ContactFinder find = new ContactFinder();

    @Id
    private Long id;

    private String name;

    private String phoneNumber;

    public Contact(Long id, String name, String phoneNumber, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public Contact() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @WhenCreated
    private Instant createdAt = Instant.now();

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public final Instant getCreatedAt() {
        return createdAt;
    }

    public final void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static class ContactFinder extends Finder<Long, Contact> {

        public Contact byId(@NotNull Long id) {
            return new QContact()
                .id.eq(id)
                .findOne();
        }

        public Contact likePhoneNumber(String phoneNumber) {
            return query().where().eq("phoneNumber", phoneNumber).findOne();
        }


        private ContactFinder() {
            super(Contact.class);
        }
    }
}
