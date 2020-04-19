package com.example.homeworktopass.Contacts;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactsContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Contact> ITEMS = new ArrayList<Contact>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 2;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(Integer.toString(item.id), item);
    }
    public static void removeItem(int position){
        int itemID = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(Integer.toString(itemID));
    }

    private static Contact createDummyItem(int position) {
        return new Contact("Joe", "Blow","14.01.2003","1", "666999888", 100 + position);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static String calling(int position){
        return ITEMS.get(position).name;
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Contact implements Parcelable {
        public final String name;
        public final String surname;
        public final String birthDate;
        public final String photoPath;
        public final String phoneNumber;
        public final int id;

        public Contact(String name, String surname, String birthDate, String photoPath, String phoneNumber, int id) {
            this.name = name;
            this.surname = surname;
            this.birthDate = birthDate;
            this.photoPath = photoPath;
            this.phoneNumber = phoneNumber;
            this.id = id;
            Log.d("OBRAZ", "obraz = " + photoPath );
        }
        public Contact(Parcel parcel){
            this.name = parcel.readString();
            this.surname = parcel.readString();
            this.birthDate = parcel.readString();
            this.photoPath = parcel.readString();
            this.phoneNumber = parcel.readString();
            this.id = parcel.readInt();
        }

        @Override
        public String toString() {
            return name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Contact> CREATOR = new Creator<Contact>() {
            @Override
            public Contact createFromParcel(Parcel in) {
                return new Contact(in);
            }

            @Override
            public Contact[] newArray(int size) {
                return new Contact[size];
            }
        };

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(birthDate);
            dest.writeString(photoPath);
            dest.writeString(phoneNumber);
            dest.writeInt(id);

        }


    }
}
