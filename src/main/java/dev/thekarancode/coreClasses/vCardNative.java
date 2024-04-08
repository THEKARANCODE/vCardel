package dev.thekarancode.coreClasses;

import dev.thekarancode.customExceptions.InvalidDateException;
import dev.thekarancode.customExceptions.InvalidDateFormatException;
import dev.thekarancode.customExceptions.InvalidEmailFormatException;

import java.util.Arrays;
import java.util.StringJoiner;

import static dev.thekarancode.utilityClasses.Handyman.*;
/*
┬  ┬┌─┐┌─┐┬─┐┌┬┐┌┐┌┌─┐┌┬┐┬┬  ┬┌─┐
└┐┌┘│  ├─┤├┬┘ │││││├─┤ │ │└┐┌┘├┤
 └┘ └─┘┴ ┴┴└──┴┘┘└┘┴ ┴ ┴ ┴ └┘ └─┘
 vCARDNATIVE
*/

/**
 * vCardNative class represents a virtual contact card. It encapsulates various details
 * about a person including identification details, communication details, address details,
 * organization details, and additional details.
 *
 * @author Karan A. Raut
 * @version 1.0
 */
public final class vCardNative {

    /*
        ┬┌┬┐┌─┐┌┐┌┌┬┐┬┌─┐┬┌─┐┌─┐┌┬┐┬┌─┐┌┐┌  ┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐
        │ ││├┤ │││ │ │├┤ ││  ├─┤ │ ││ ││││   ││├┤  │ ├─┤││  └─┐
        ┴─┴┘└─┘┘└┘ ┴ ┴└  ┴└─┘┴ ┴ ┴ ┴└─┘┘└┘  ─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘
        IDENTIFICATION DETAILS
    */

    /**
     * The prefix associated with the name (e.g., Mr., Ms., Dr.).
     */
    private String prefix;

    /**
     * The person's first name.
     */
    private String firstName;

    /**
     * The person's middle name (optional).
     */
    private String middleName;

    /**
     * The person's last name.
     */
    private String lastName;

    /**
     * The suffix associated with the name (e.g., Jr., Sr., III).
     */
    private String suffix;

    /**
     * The person's nickname (optional).
     */
    private String nickName;

    /**
     * The person's date of birth.
     */
    private String dob;

    /*
        ┌─┐┌─┐┌┬┐┌┬┐┬ ┬┌┐┌┬┌─┐┌─┐┌┬┐┬┌─┐┌┐┌  ┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐
        │  │ ││││││││ ││││││  ├─┤ │ ││ ││││   ││├┤  │ ├─┤││  └─┐
        └─┘└─┘┴ ┴┴ ┴└─┘┘└┘┴└─┘┴ ┴ ┴ ┴└─┘┘└┘  ─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘
        COMMUNICATION DETAILS
    */

    /**
     * The preferred phone number.
     */
    private String prefPhNum;

    /**
     * The primary mobile number.
     */
    private String priMobNum;

    /**
     * The secondary mobile number.
     */
    private String secMobNum;

    /**
     * The home telephone number.
     */
    private String homeTelNum;

    /**
     * The personal email address.
     */
    private String personalEmail;

    /**
     * The work telephone number.
     */
    private String workTelNum;

    /**
     * The work email address.
     */
    private String workEmail;

    /*
        ┌─┐┌┬┐┌┬┐┬─┐┌─┐┌─┐┌─┐  ┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐
        ├─┤ ││ ││├┬┘├┤ └─┐└─┐   ││├┤  │ ├─┤││  └─┐
        ┴ ┴─┴┘─┴┘┴└─└─┘└─┘└─┘  ─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘
        ADDRESS DETAILS
    */
    /**
     * The street address of the home.
     */
    private String homeStreet;

    /**
     * The city of the home address.
     */
    private String homeCity;

    /**
     * The state or region of the home address.
     */
    private String homeState;

    /**
     * The postal code of the home address.
     */
    private String homePostalCode;

    /**
     * The country of the home address.
     */
    private String homeCountry;

    /**
     * The street address of the workplace.
     */
    private String workStreet;

    /**
     * The city of the workplace address.
     */
    private String workCity;

    /**
     * The state or region of the workplace address.
     */
    private String workState;

    /**
     * The postal code of the workplace address.
     */
    private String workPostalCode;

    /**
     * The country of the workplace address.
     */
    private String workCountry;

    /*
        ┌─┐┬─┐┌─┐┌─┐┌┐┌┬┌─┐┌─┐┌┬┐┬┌─┐┌┐┌  ┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐
        │ │├┬┘│ ┬├─┤││││┌─┘├─┤ │ ││ ││││   ││├┤  │ ├─┤││  └─┐
        └─┘┴└─└─┘┴ ┴┘└┘┴└─┘┴ ┴ ┴ ┴└─┘┘└┘  ─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘
        ORGANIZATION DETAILS
    */

    /**
     * The role within the organization.
     */
    private String role;

    /**
     * The title within the organization.
     */
    private String title;

    /**
     * The department within the organization.
     */
    private String department;

    /**
     * The name of the organization.
     */
    private String organization;

    /*
        ┌─┐┌┬┐┌┬┐┬┌┬┐┬┌─┐┌┐┌┌─┐┬    ┌┬┐┌─┐┌┬┐┌─┐┬┬  ┌─┐
        ├─┤ ││ │││ │ ││ ││││├─┤│     ││├┤  │ ├─┤││  └─┐
        ┴ ┴─┴┘─┴┘┴ ┴ ┴└─┘┘└┘┴ ┴┴─┘  ─┴┘└─┘ ┴ ┴ ┴┴┴─┘└─┘
        ADDITIONAL DETAILS
    */

    /**
     * Any additional note or information.
     */
    private String note;

    /**
     * The URL associated with the person.
     */
    private String url;

    /**
     * Custom labels for additional information (e.g., "Friend", "Colleague").
     */
    private String[] labels;

    /**
     * The gender of the person.
     */
    private String gender;

    /*
        ┌─┐┌─┐┌┬┐┌┬┐┌─┐┬─┐┌─┐
        └─┐├┤  │  │ ├┤ ├┬┘└─┐
        └─┘└─┘ ┴  ┴ └─┘┴└─└─┘
        SETTERS
    */

    /**
     * Sets the identification details of the entity represented by the vCard.
     * This method should be used for both individual persons and organizations.
     *
     * @param prefix     The prefix associated with the name (e.g., Mr., Ms., Dr.).
     * @param firstName  The first name of the entity.
     * @param middleName The middle name of the entity (optional).
     * @param lastName   The last name of the entity.
     * @param suffix     The suffix associated with the name (e.g., Jr., Sr., III).
     * @param nickName   The nickname of the entity (optional).
     * @param dob        The date of birth of the entity (for persons) or establishment date (for organizations).
     */
    public void setIdentificationDetails(String prefix, String firstName, String middleName, String lastName, String suffix, String nickName, String dob) throws InvalidDateFormatException, InvalidDateException {
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.nickName = nickName;
        this.dob = dob;
    }

    /**
     * Sets the communication details of the entity represented by the vCard.
     * This method should be used for both individual persons and organizations.
     *
     * @param prefPhNum     The preferred phone number of the entity.
     * @param priMobNum     The primary mobile number of the entity.
     * @param secMobNum     The secondary mobile number of the entity.
     * @param homeTelNum    The home telephone number of the entity.
     * @param personalEmail The personal email address of the entity.
     * @param workTelNum    The work telephone number of the entity.
     * @param workEmail     The work email address of the entity.
     */
    public void setCommunicationDetails(String prefPhNum, String priMobNum, String secMobNum, String homeTelNum, String personalEmail, String workTelNum, String workEmail) throws InvalidEmailFormatException {
        this.prefPhNum = prefPhNum;
        this.priMobNum = priMobNum;
        this.secMobNum = secMobNum;
        this.homeTelNum = homeTelNum;
        this.personalEmail = personalEmail;
        this.workTelNum = workTelNum;
        this.workEmail = workEmail;
    }

    /**
     * Sets the address details of the entity represented by the vCard.
     * This method should be used for both individual persons and organizations.
     *
     * @param homeStreet     The street address of the entity's home or headquarters.
     * @param homeCity       The city of the entity's home or headquarters.
     * @param homeState      The state or region of the entity's home or headquarters.
     * @param homePostalCode The postal code of the entity's home or headquarters.
     * @param homeCountry    The country of the entity's home or headquarters.
     * @param workStreet     The street address of the entity's work or branch office.
     * @param workCity       The city of the entity's work or branch office.
     * @param workState      The state or region of the entity's work or branch office.
     * @param workPostalCode The postal code of the entity's work or branch office.
     * @param workCountry    The country of the entity's work or branch office.
     */
    public void setAddressDetails(String homeStreet, String homeCity, String homeState, String homePostalCode, String homeCountry, String workStreet, String workCity, String workState, String workPostalCode, String workCountry) {
        this.homeStreet = homeStreet;
        this.homeCity = homeCity;
        this.homeState = homeState;
        this.homePostalCode = homePostalCode;
        this.homeCountry = homeCountry;
        this.workStreet = workStreet;
        this.workCity = workCity;
        this.workState = workState;
        this.workPostalCode = workPostalCode;
        this.workCountry = workCountry;
    }

    /**
     * Sets the organization details of the entity represented by the vCard.
     * This method should be used for organizations only.
     *
     * @param role         The role within the organization.
     * @param title        The title within the organization.
     * @param department   The department within the organization.
     * @param organization The name of the organization.
     */
    public void setOrganizationDetails(String role, String title, String department, String organization) {
        this.role = role;
        this.title = title;
        this.department = department;
        this.organization = organization;
    }

    /**
     * Sets any additional note or information about the entity represented by the vCard.
     *
     * @param note The note to be set.
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Sets custom labels for additional information about the entity represented by the vCard.
     *
     * @param labels The custom labels to be set.
     */
    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    /**
     * Sets the URL associated with the entity represented by the vCard.
     *
     * @param url The URL to be set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the gender of the individual represented by the vCard.
     * This method should be used for individuals only.
     *
     * @param gender The gender to be set.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /*
        ┌─┐┌─┐┌┬┐┌┬┐┌─┐┬─┐┌─┐
        │ ┬├┤  │  │ ├┤ ├┬┘└─┐
        └─┘└─┘ ┴  ┴ └─┘┴└─└─┘
        GETTERS
    */

    /**
     * Retrieves the prefix associated with the entity's name.
     *
     * @return The prefix associated with the name.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Retrieves the first name of the entity.
     *
     * @return The first name of the entity.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the middle name of the entity.
     *
     * @return The middle name of the entity. Returns null if not set.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Retrieves the last name of the entity.
     *
     * @return The last name of the entity.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the suffix associated with the entity's name.
     *
     * @return The suffix associated with the name.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Retrieves the nickname of the entity.
     *
     * @return The nickname of the entity. Returns null if not set.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Retrieves the date of birth of the entity.
     *
     * @return The date of birth of the entity.
     */
    public String getDob() {
        return dob;
    }

    /**
     * Retrieves the preferred phone number of the entity.
     *
     * @return The preferred phone number of the entity.
     */
    public String getPrefPhNum() {
        return prefPhNum;
    }

    /**
     * Retrieves the primary mobile number of the entity.
     *
     * @return The primary mobile number of the entity.
     */
    public String getPriMobNum() {
        return priMobNum;
    }

    /**
     * Retrieves the secondary mobile number of the entity.
     *
     * @return The secondary mobile number of the entity.
     */
    public String getSecMobNum() {
        return secMobNum;
    }

    /**
     * Retrieves the home telephone number of the entity.
     *
     * @return The home telephone number of the entity.
     */
    public String getHomeTelNum() {
        return homeTelNum;
    }

    /**
     * Retrieves the personal email address of the entity.
     *
     * @return The personal email address of the entity.
     */
    public String getPersonalEmail() {
        return personalEmail;
    }

    /**
     * Retrieves the work telephone number of the entity.
     *
     * @return The work telephone number of the entity.
     */
    public String getWorkTelNum() {
        return workTelNum;
    }

    /**
     * Retrieves the work email address of the entity.
     *
     * @return The work email address of the entity.
     */
    public String getWorkEmail() {
        return workEmail;
    }

    /**
     * Retrieves the home street address of the entity.
     *
     * @return The home street address of the entity.
     */
    public String getHomeStreet() {
        return homeStreet;
    }

    /**
     * Retrieves the home city of the entity.
     *
     * @return The home city of the entity.
     */
    public String getHomeCity() {
        return homeCity;
    }

    /**
     * Retrieves the home state of the entity.
     *
     * @return The home state of the entity.
     */
    public String getHomeState() {
        return homeState;
    }

    /**
     * Retrieves the home postal code of the entity.
     *
     * @return The home postal code of the entity.
     */
    public String getHomePostalCode() {
        return homePostalCode;
    }

    /**
     * Retrieves the home country of the entity.
     *
     * @return The home country of the entity.
     */
    public String getHomeCountry() {
        return homeCountry;
    }

    /**
     * Retrieves the work street address of the entity.
     *
     * @return The work street address of the entity.
     */
    public String getWorkStreet() {
        return workStreet;
    }

    /**
     * Retrieves the work city of the entity.
     *
     * @return The work city of the entity.
     */
    public String getWorkCity() {
        return workCity;
    }

    /**
     * Retrieves the work state of the entity.
     *
     * @return The work state of the entity.
     */
    public String getWorkState() {
        return workState;
    }

    /**
     * Retrieves the work postal code of the entity.
     *
     * @return The work postal code of the entity.
     */
    public String getWorkPostalCode() {
        return workPostalCode;
    }

    /**
     * Retrieves the work country of the entity.
     *
     * @return The work country of the entity.
     */
    public String getWorkCountry() {
        return workCountry;
    }

    /**
     * Retrieves the role of the entity within the organization.
     *
     * @return The role of the entity.
     */
    public String getRole() {
        return role;
    }

    /**
     * Retrieves the title of the entity.
     *
     * @return The title of the entity.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the department of the entity within the organization.
     *
     * @return The department of the entity.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Retrieves the organization the entity is associated with.
     *
     * @return The organization the entity is associated with.
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Retrieves additional notes or remarks about the entity.
     *
     * @return Additional notes or remarks about the entity.
     */
    public String getNote() {
        return note;
    }

    /**
     * Retrieves the URL associated with the entity.
     *
     * @return The URL associated with the entity.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Retrieves the labels associated with the entity.
     *
     * @return The labels associated with the entity.
     */
    public String[] getLabels() {
        return labels;
    }

    /**
     * Retrieves the gender of the entity.
     *
     * @return The gender of the entity.
     */
    public String getGender() {
        return gender;
    }

    /*
        ┬ ┬┌┬┐┬┬  ┬┌┬┐┬ ┬  ┌┬┐┌─┐┌┬┐┬ ┬┌─┐┌┬┐┌─┐
        │ │ │ ││  │ │ └┬┘  │││├┤  │ ├─┤│ │ ││└─┐
        └─┘ ┴ ┴┴─┘┴ ┴  ┴   ┴ ┴└─┘ ┴ ┴ ┴└─┘─┴┘└─┘
        UTILITY METHODS
    */

    /**
     * Checks if the vCardNative instance is empty, meaning that all of its fields are either
     * null or blank.
     *
     * @return {@code true} if all fields of the vCardNative instance are null or blank,
     *         {@code false} otherwise.
     */
    public boolean isEmpty() {
        return (isNullOrBlank(prefix) && isNullOrBlank(firstName) && isNullOrBlank(middleName) &&
                isNullOrBlank(lastName) && isNullOrBlank(suffix) && isNullOrBlank(nickName) &&
                isNullOrBlank(dob) && isNullOrBlank(prefPhNum) && isNullOrBlank(priMobNum) &&
                isNullOrBlank(secMobNum) && isNullOrBlank(homeTelNum) && isNullOrBlank(personalEmail) &&
                isNullOrBlank(workTelNum) && isNullOrBlank(workEmail) && isNullOrBlank(homeStreet) &&
                isNullOrBlank(homeCity) && isNullOrBlank(homeState) && isNullOrBlank(homePostalCode) &&
                isNullOrBlank(homeCountry) && isNullOrBlank(workStreet) && isNullOrBlank(workCity) &&
                isNullOrBlank(workState) && isNullOrBlank(workPostalCode) && isNullOrBlank(workCountry) &&
                isNullOrBlank(role) && isNullOrBlank(title) && isNullOrBlank(department) &&
                isNullOrBlank(organization) && isNullOrBlank(note) && isNullOrBlank(url) &&
                isNullOrBlank(labels) && isNullOrBlank(gender));
    }

    /**
     * Generates a string representation of the entity's details.
     * It includes identification details, communication details,
     * address details, organization details, and additional details.
     *
     * @return A string containing the vCard representation of the entity.
     */
    @Override
    public String toString() {
        StringJoiner vCardNativeToString = new StringJoiner("\n");

        // Identification Details
        vCardNativeToString.add("Identification Details {")
                .add("\tPrefix: " + prefix)
                .add("\tFirst Name: " + firstName)
                .add("\tMiddle Name: " + middleName)
                .add("\tLast Name: " + lastName)
                .add("\tSuffix: " + suffix)
                .add("\tNick Name: " + nickName)
                .add("\tDate of Birth: " + dob)
                .add("}");

        // Communication Details
        vCardNativeToString.add("Communication Details {")
                .add("\tPreferred Phone Number: " + prefPhNum)
                .add("\tPrimary Mobile Number: " + priMobNum)
                .add("\tSecondary Mobile Number: " + secMobNum)
                .add("\tHome Telephone Number: " + homeTelNum)
                .add("\tPersonal Email: " + personalEmail)
                .add("\tWork Telephone Number: " + workTelNum)
                .add("\tWork Email: " + workEmail)
                .add("}");

        // Address Details
        vCardNativeToString.add("Address Details {")
                .add("\tHome Street: " + homeStreet)
                .add("\tHome City: " + homeCity)
                .add("\tHome State: " + homeState)
                .add("\tHome Postal Code: " + homePostalCode)
                .add("\tHome Country: " + homeCountry)
                .add("\tWork Street: " + workStreet)
                .add("\tWork City: " + workCity)
                .add("\tWork State: " + workState)
                .add("\tWork Postal Code: " + workPostalCode)
                .add("\tWork Country: " + workCountry)
                .add("}");

        // Organization Details
        vCardNativeToString.add("Organization Details {")
                .add("\tRole: " + role)
                .add("\tTitle: " + title)
                .add("\tDepartment: " + department)
                .add("\tOrganization: " + organization)
                .add("}");

        // Additional Details
        vCardNativeToString.add("Additional Details {")
                .add("\tNote: " + note)
                .add("\tURL: " + url)
                .add("\tLabels: " + Arrays.toString(labels))
                .add("\tGender: " + gender)
                .add("}");
        return vCardNativeToString.toString();
    }

    /*
    ┌─┐┌┬┐┌─┐┌┬┐┬┌─┐  ┬  ┬┌─┐┬─┐┬┌─┐┌┐ ┬  ┌─┐
    └─┐ │ ├─┤ │ ││    └┐┌┘├─┤├┬┘│├─┤├┴┐│  ├┤
    └─┘ ┴ ┴ ┴ ┴ ┴└─┘   └┘ ┴ ┴┴└─┴┴ ┴└─┘┴─┘└─┘
    */

    /**
     * A static counter to keep track of the number of vCardNative instances created.
     */
    private static int vCardNativeInstanceCounter;

    /*
        ┬┌┐┌┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐  ┬┌┐┌┬┌┬┐┬┌─┐┬  ┬┌─┐┌─┐┬─┐
        ││││└─┐ │ ├─┤││││  ├┤   │││││ │ │├─┤│  │┌─┘├┤ ├┬┘
        ┴┘└┘└─┘ ┴ ┴ ┴┘└┘└─┘└─┘  ┴┘└┘┴ ┴ ┴┴ ┴┴─┘┴└─┘└─┘┴└─
        INSTANCE INITIALIZER
    */

    /*
   Initializes a new instance of vCardNative and increments the instance counter.
   This initializer is automatically called whenever a new instance of vCardNative is created.
   It prints a message to indicate the creation of the instance.
   */ {
        vCardNativeInstanceCounter++;
        System.out.println("vCardNative instance $vC4rdN4tiv3#" + vCardNativeInstanceCounter + "->" + this.hashCode());
    }
}