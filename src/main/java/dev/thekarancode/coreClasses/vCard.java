package dev.thekarancode.coreClasses;


import dev.thekarancode.utilityClasses.Handyman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
┬  ┬┌─┐┌─┐┬─┐┌┬┐
└┐┌┘│  ├─┤├┬┘ ││
 └┘ └─┘┴ ┴┴└──┴┘
vCARD
*/

/**
 * The {@code vCard} class represents a vCard (Virtual Contact File) wrapper around the {@code vCardNative} class,
 * providing functionality to compose a vCard according to the vCard syntax (version 3.0).
 * This class facilitates the creation of vCard objects by converting data from the {@code vCardNative} format into
 * a properly formatted vCard string according to the vCard specification.
 * <p>
 * A vCard typically contains information such as contact details, communication details, address details,
 * organization details, and additional details like notes, URLs, labels, and gender information.
 * </p>
 * <p>
 * The class allows customization of vCard composition by specifying whether to capitalize the composed vCard
 * and specifying characters to escape during composition.
 * </p>
 * <p>
 * Instances of this class are immutable once initialized.
 * </p>
 *
 * @author Karan A. Raut
 * @version 1.0
 */
public final class vCard {

    /*
        ┬┌┐┌┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐  ┬  ┬┌─┐┬─┐┬┌─┐┌┐ ┬  ┌─┐┌─┐
        ││││└─┐ │ ├─┤││││  ├┤   └┐┌┘├─┤├┬┘│├─┤├┴┐│  ├┤ └─┐
        ┴┘└┘└─┘ ┴ ┴ ┴┘└┘└─┘└─┘   └┘ ┴ ┴┴└─┴┴ ┴└─┘┴─┘└─┘└─┘
        INSTANCE VARIABLES
    */

    /**
     * Characters to escape when composing the vCard.
     */
    private final String charsToEscape;

    /**
     * Indicates whether to capitalize the composed vCard.
     */
    private final boolean capitalize;

    /**
     * The core vCard instance holding the contact information.
     */
    private final vCardNative core_vCard;

    /*
        ┌─┐┌─┐┌┐┌┌─┐┌┬┐┬─┐┬ ┬┌─┐┌┬┐┌─┐┬─┐┌─┐
        │  │ ││││└─┐ │ ├┬┘│ ││   │ │ │├┬┘└─┐
        └─┘└─┘┘└┘└─┘ ┴ ┴└─└─┘└─┘ ┴ └─┘┴└─└─┘
        CONSTRUCTORS
    */

    /**
     * Constructs a {@code vCard} object with the provided {@code source_vCard}, {@code capitalize}, and {@code charsToEscape}.
     * <p>
     * This constructor allows customization of vCard composition by specifying whether to capitalize the composed vCard
     * and specifying characters to escape during composition.
     * </p>
     *
     * @param source_vCard  The source {@code vCardNative} object containing contact information.
     * @param capitalize    Indicates whether to capitalize the composed vCard.
     * @param charsToEscape Characters to escape when composing the vCard.
     */
    public vCard(vCardNative source_vCard, boolean capitalize, String charsToEscape) {
        this.core_vCard = source_vCard;
        this.capitalize = capitalize;
        this.charsToEscape = charsToEscape;
    }

    /**
     * Constructs a {@code vCard} object with the provided {@code source_vCard} and {@code capitalize}.
     * <p>
     * This constructor sets default characters to escape during composition.
     * </p>
     *
     * @param source_vCard The source {@code vCardNative} object containing contact information.
     * @param capitalize   Indicates whether to capitalize the composed vCard.
     */
    public vCard(vCardNative source_vCard, boolean capitalize) {
        this.core_vCard = source_vCard;
        this.capitalize = capitalize;
        this.charsToEscape = "\\:;,";
    }

    /**
     * Constructs a {@code vCard} object with the provided {@code source_vCard}.
     * <p>
     * This constructor sets default characters to escape and does not capitalize the composed vCard.
     * </p>
     *
     * @param source_vCard The source {@code vCardNative} object containing contact information.
     */
    public vCard(vCardNative source_vCard) {
        this.core_vCard = source_vCard;
        this.capitalize = false;
        this.charsToEscape = "\\:;,";
    }

    /*
        ┌┬┐┌─┐┌┬┐┬ ┬┌─┐┌┬┐  ┌─┐┌─┐┬─┐  ┌─┐┌─┐┌┬┐┌─┐┌─┐┌─┐┬┌┐┌┌─┐  ┬  ┬┌─┐┌─┐┬─┐┌┬┐
        │││├┤  │ ├─┤│ │ ││  ├┤ │ │├┬┘  │  │ ││││├─┘│ │└─┐│││││ ┬  └┐┌┘│  ├─┤├┬┘ ││
        ┴ ┴└─┘ ┴ ┴ ┴└─┘─┴┘  └  └─┘┴└─  └─┘└─┘┴ ┴┴  └─┘└─┘┴┘└┘└─┘   └┘ └─┘┴ ┴┴└──┴┘
        METHOD FOR COMPOSING vCARD
    */

    /**
     * Composes the vCard string according to the vCard syntax (version 3.0).
     * <p>
     * This method constructs a vCard string by concatenating various properties in accordance with the vCard syntax.
     * Each property is formatted according to the vCard specification, and lines are folded as needed to ensure
     * compliance with the 75-character line length limit.
     * </p>
     * <p>
     * The vCard string includes the following properties:
     * <ul>
     * <li>FN (Formatted Name)</li>
     * <li>N (Name)</li>
     * <li>NICKNAME</li>
     * <li>BDAY (Birthday)</li>
     * <li>TEL (Telephone Number) with various types (PREF, CELL, HOME, WORK)</li>
     * <li>EMAIL with types (X-PERSONAL, WORK)</li>
     * <li>ADR (Address) with types (HOME, WORK)</li>
     * <li>TITLE (Job Title)</li>
     * <li>ROLE (Role within the organization)</li>
     * <li>ORG (Organization) with department</li>
     * <li>NOTE (Additional notes)</li>
     * <li>URL (Website URL)</li>
     * <li>X-LABEL (Labels)</li>
     * <li>X-GENDER (Gender)</li>
     * </ul>
     * </p>
     * <p>
     * The method utilizes utility methods to process values, apply capitalization, and escape characters as necessary,
     * ensuring the proper formatting of each property.
     * </p>
     * <p>
     * The composed vCard string is returned with each property folded into multiple lines as needed to comply with
     * the vCard specification's line length limit.
     * </p>
     *
     * @return The composed vCard string adhering to the vCard syntax.
     */
    public String compose_vCard() {
        StringBuilder composed_vCard = new StringBuilder();
        composed_vCard
                .append("BEGIN:VCARD").append("\n")
                .append("VERSION:3.0").append("\n")
                .append(Handyman.foldLine(compose_FN_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_N_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_NICKNAME_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_BDAY_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TELPREF_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TELCELL1_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TELCELL2_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TELHOME_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_EMAILPERSONAL_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TELWORK_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_EMAILWORK_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_ADRHOME_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_ADRWORK_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_TITLE_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_ROLE_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_ORG_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_NOTE_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_URL_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_LABEL_Property(), 75)).append("\n")
                .append(Handyman.foldLine(compose_GENDER_Property(), 75)).append("\n");
        return composed_vCard.toString();
    }

    /*
    ┌┬┐┌─┐┌┬┐┬ ┬┌─┐┌┬┐┌─┐  ┌─┐┌─┐┬─┐  ┌─┐┌─┐┌┬┐┌─┐┌─┐┌─┐┬┌┐┌┌─┐  ┬  ┬┌─┐┌─┐┬─┐┌┬┐  ┌─┐┬─┐┌─┐┌─┐┌─┐┬─┐┌┬┐┬┌─┐┌─┐
    │││├┤  │ ├─┤│ │ ││└─┐  ├┤ │ │├┬┘  │  │ ││││├─┘│ │└─┐│││││ ┬  └┐┌┘│  ├─┤├┬┘ ││  ├─┘├┬┘│ │├─┘├┤ ├┬┘ │ │├┤ └─┐
    ┴ ┴└─┘ ┴ ┴ ┴└─┘─┴┘└─┘  └  └─┘┴└─  └─┘└─┘┴ ┴┴  └─┘└─┘┴┘└┘└─┘   └┘ └─┘┴ ┴┴└──┴┘  ┴  ┴└─└─┘┴  └─┘┴└─ ┴ ┴└─┘└─┘
    ┌─┐┌─┐┌─┐┌─┐┬─┐┌┬┐┬┌┐┌┌─┐  ┌┬┐┌─┐  ┬  ┬┌─┐┌─┐┬─┐┌┬┐  ┌─┐┬ ┬┌┐┌┌┬┐┌─┐─┐ ┬
    ├─┤│  │  │ │├┬┘ │││││││ ┬   │ │ │  └┐┌┘│  ├─┤├┬┘ ││  └─┐└┬┘│││ │ ├─┤┌┴┬┘
    ┴ ┴└─┘└─┘└─┘┴└──┴┘┴┘└┘└─┘   ┴ └─┘   └┘ └─┘┴ ┴┴└──┴┘  └─┘ ┴ ┘└┘ ┴ ┴ ┴┴ └─
    METHODS FOR COMPOSING vCARD PROPERTIES ACCORDING TO THE vCARD SYNTAX
    */

    /**
     * Composes the formatted name (FN) property of the vCard.
     * <p>
     * The FN property represents the full name of the vCard holder. This method constructs the FN property
     * by concatenating the processed prefix, first name, middle name, last name, and suffix retrieved from
     * the core_vCard instance. Each component is properly formatted and appended to the FN property string.
     * </p>
     *
     * @return The formatted FN property string.
     */
    private String compose_FN_Property() {
        StringBuilder composedProperty = new StringBuilder();

        String processedPrefix = processVal(core_vCard.getPrefix());
        String processedFirstName = processVal(core_vCard.getFirstName());
        String processedMiddleName = processVal(core_vCard.getMiddleName());
        String processedLastName = processVal(core_vCard.getLastName());
        String processedSuffix = processVal(core_vCard.getSuffix());

        composedProperty
                .append("FN:")
                .append(!processedPrefix.isEmpty() ? processedPrefix + " " : "")
                .append(!processedFirstName.isEmpty() ? processedFirstName + " " : "")
                .append(!processedMiddleName.isEmpty() ? processedMiddleName + " " : "")
                .append(!processedLastName.isEmpty() ? processedLastName + " " : "")
                .append(processedSuffix);
        return composedProperty.toString();
    }

    /**
     * Composes the name (N) property of the vCard.
     * <p>
     * The N property represents the structured name of the vCard holder. This method constructs the N property
     * by concatenating the processed components (last name, first name, middle name, prefix, suffix) retrieved from
     * the core_vCard instance. Each component is properly formatted and separated by semicolons.
     * </p>
     *
     * @return The formatted N property string.
     */
    private String compose_N_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("N:")
                .append(processVal(core_vCard.getLastName())).append(";")
                .append(processVal(core_vCard.getFirstName())).append(";")
                .append(processVal(core_vCard.getMiddleName())).append(";")
                .append(processVal(core_vCard.getPrefix())).append(";")
                .append(processVal(core_vCard.getSuffix()));
        return composedProperty.toString();
    }

    /**
     * Composes the nickname (NICKNAME) property of the vCard.
     * <p>
     * The NICKNAME property represents the nickname of the vCard holder. This method constructs the NICKNAME property
     * by processing the nickname retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted NICKNAME property string.
     */
    private String compose_NICKNAME_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("NICKNAME:")
                .append(processVal(core_vCard.getNickName()));
        return composedProperty.toString();
    }

    /**
     * Composes the birthday (BDAY) property of the vCard.
     * <p>
     * The BDAY property represents the birthday of the vCard holder. This method constructs the BDAY property
     * by processing the date of birth retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted BDAY property string.
     */
    private String compose_BDAY_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("BDAY:")
                .append(processVal(core_vCard.getDob()));
        return composedProperty.toString();
    }

    /**
     * Composes the preferred telephone number (TEL;TYPE=PREF) property of the vCard.
     * <p>
     * The TEL;TYPE=PREF property represents the preferred telephone number of the vCard holder. This method constructs
     * the TEL;TYPE=PREF property by processing the preferred phone number retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TEL;TYPE=PREF property string.
     */
    private String compose_TELPREF_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TEL;TYPE=PREF:")
                .append(processVal(core_vCard.getPrefPhNum()));
        return composedProperty.toString();
    }

    /**
     * Composes the first cellular telephone number (TEL;TYPE=CELL) property of the vCard.
     * <p>
     * The TEL;TYPE=CELL property represents the first cellular telephone number of the vCard holder. This method constructs
     * the TEL;TYPE=CELL property by processing the primary mobile number retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TEL;TYPE=CELL property string.
     */
    private String compose_TELCELL1_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TEL;TYPE=CELL:")
                .append(processVal(core_vCard.getPriMobNum()));
        return composedProperty.toString();
    }

    /**
     * Composes the second cellular telephone number (TEL;TYPE=CELL) property of the vCard.
     * <p>
     * The TEL;TYPE=CELL property represents the second cellular telephone number of the vCard holder. This method constructs
     * the TEL;TYPE=CELL property by processing the secondary mobile number retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TEL;TYPE=CELL property string.
     */
    private String compose_TELCELL2_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TEL;TYPE=CELL:")
                .append(processVal(core_vCard.getSecMobNum()));
        return composedProperty.toString();
    }

    /**
     * Composes the home telephone number (TEL;TYPE=HOME) property of the vCard.
     * <p>
     * The TEL;TYPE=HOME property represents the home telephone number of the vCard holder. This method constructs
     * the TEL;TYPE=HOME property by processing the home telephone number retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TEL;TYPE=HOME property string.
     */
    private String compose_TELHOME_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TEL;TYPE=HOME:")
                .append(processVal(core_vCard.getHomeTelNum()));
        return composedProperty.toString();
    }

    /**
     * Composes the personal email address (EMAIL;TYPE=X-PERSONAL) property of the vCard.
     * <p>
     * The EMAIL;TYPE=X-PERSONAL property represents the personal email address of the vCard holder. This method constructs
     * the EMAIL;TYPE=X-PERSONAL property by processing the personal email retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted EMAIL;TYPE=X-PERSONAL property string.
     */
    private String compose_EMAILPERSONAL_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("EMAIL;TYPE=X-PERSONAL:")
                .append(processVal(core_vCard.getPersonalEmail()));
        return composedProperty.toString();
    }

    /**
     * Composes the work telephone number (TEL;TYPE=WORK) property of the vCard.
     * <p>
     * The TEL;TYPE=WORK property represents the work telephone number of the vCard holder. This method constructs
     * the TEL;TYPE=WORK property by processing the work telephone number retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TEL;TYPE=WORK property string.
     */
    private String compose_TELWORK_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TEL;TYPE=WORK:")
                .append(processVal(core_vCard.getWorkTelNum()));
        return composedProperty.toString();
    }

    /**
     * Composes the work email address (EMAIL;TYPE=WORK) property of the vCard.
     * <p>
     * The EMAIL;TYPE=WORK property represents the work email address of the vCard holder. This method constructs
     * the EMAIL;TYPE=WORK property by processing the work email retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted EMAIL;TYPE=WORK property string.
     */
    private String compose_EMAILWORK_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("EMAIL;TYPE=WORK:")
                .append(processVal(core_vCard.getWorkEmail()));
        return composedProperty.toString();
    }

    /**
     * Composes the home address (ADR;TYPE=HOME) property of the vCard.
     * <p>
     * The ADR;TYPE=HOME property represents the home address of the vCard holder. This method constructs
     * the ADR;TYPE=HOME property by processing the components of the home address retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted ADR;TYPE=HOME property string.
     */
    private String compose_ADRHOME_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("ADR;TYPE=HOME:;;")
                .append(processVal(core_vCard.getHomeStreet())).append(";")
                .append(processVal(core_vCard.getHomeCity())).append(";")
                .append(processVal(core_vCard.getHomeState())).append(";")
                .append(processVal(core_vCard.getHomePostalCode())).append(";")
                .append(processVal(core_vCard.getHomeCountry()));
        return composedProperty.toString();
    }

    /**
     * Composes the work address (ADR;TYPE=WORK) property of the vCard.
     * <p>
     * The ADR;TYPE=WORK property represents the work address of the vCard holder. This method constructs
     * the ADR;TYPE=WORK property by processing the components of the work address retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted ADR;TYPE=WORK property string.
     */
    private String compose_ADRWORK_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("ADR;TYPE=WORK:;;")
                .append(processVal(core_vCard.getWorkStreet())).append(";")
                .append(processVal(core_vCard.getWorkCity())).append(";")
                .append(processVal(core_vCard.getWorkState())).append(";")
                .append(processVal(core_vCard.getWorkPostalCode())).append(";")
                .append(processVal(core_vCard.getWorkCountry()));
        return composedProperty.toString();
    }

    /**
     * Composes the title (TITLE) property of the vCard.
     * <p>
     * The TITLE property represents the title of the vCard holder. This method constructs
     * the TITLE property by processing the title retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted TITLE property string.
     */
    private String compose_TITLE_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("TITLE:")
                .append(processVal(core_vCard.getTitle()));
        return composedProperty.toString();
    }

    /**
     * Composes the role (ROLE) property of the vCard.
     * <p>
     * The ROLE property represents the role or occupation of the vCard holder. This method constructs
     * the ROLE property by processing the role retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted ROLE property string.
     */
    private String compose_ROLE_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("ROLE:")
                .append(processVal(core_vCard.getRole()));
        return composedProperty.toString();
    }

    /**
     * Composes the organization (ORG) property of the vCard.
     * <p>
     * The ORG property represents the organization or company information of the vCard holder. This method constructs
     * the ORG property by processing the organization and department retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted ORG property string.
     */
    private String compose_ORG_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("ORG:")
                .append(processVal(core_vCard.getOrganization())).append(";")
                .append(processVal(core_vCard.getDepartment()));
        return composedProperty.toString();
    }

    /**
     * Composes the note (NOTE) property of the vCard.
     * <p>
     * The NOTE property represents additional notes or comments related to the vCard holder. This method constructs
     * the NOTE property by processing the note retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted NOTE property string.
     */
    private String compose_NOTE_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("NOTE:")
                .append(processVal(core_vCard.getNote()));
        return composedProperty.toString();
    }

    /**
     * Composes the URL (URL) property of the vCard.
     * <p>
     * The URL property represents the website URL associated with the vCard holder. This method constructs
     * the URL property by processing the URL retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted URL property string.
     */
    private String compose_URL_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("URL:")
                .append(processVal(core_vCard.getUrl()));
        return composedProperty.toString();
    }

    /**
     * Composes the custom label (X-LABEL) property of the vCard.
     * <p>
     * The X-LABEL property represents custom labels associated with the vCard holder. This method constructs
     * the X-LABEL property by processing the labels retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted X-LABEL property string.
     */
    private String compose_LABEL_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty.append("X-LABEL:");
        for (String label : core_vCard.getLabels()) {
            String processedLabel = processVal(label);
            composedProperty.append(!processedLabel.isEmpty() ? processedLabel + ";" : "");
        }
        composedProperty.append(processVal("created_by_vCardel_Alpha"));
        return composedProperty.toString();
    }

    /**
     * Composes the custom gender (X-GENDER) property of the vCard.
     * <p>
     * The X-GENDER property represents the gender information of the vCard holder. This method constructs
     * the X-GENDER property by processing the gender retrieved from the core_vCard instance.
     * </p>
     *
     * @return The formatted X-GENDER property string.
     */
    private String compose_GENDER_Property() {
        StringBuilder composedProperty = new StringBuilder();
        composedProperty
                .append("X-GENDER:")
                .append(processVal(core_vCard.getGender()));
        return composedProperty.toString();
    }

    /*
        ┬ ┬┌┬┐┬┬  ┬┌┬┐┬ ┬  ┌┬┐┌─┐┌┬┐┬ ┬┌─┐┌┬┐┌─┐
        │ │ │ ││  │ │ └┬┘  │││├┤  │ ├─┤│ │ ││└─┐
        └─┘ ┴ ┴┴─┘┴ ┴  ┴   ┴ ┴└─┘ ┴ ┴ ┴└─┘─┴┘└─┘
        UTILITY METHODS
    */

    /**
     * Processes the input string by escaping special characters, capitalizing if specified, and applying character escaping.
     *
     * @param inputString The input string to be processed.
     * @return The processed string with escaped characters and applied capitalization.
     */
    private String processVal(String inputString) {
        String processedVal;
        if (inputString != null && !inputString.isEmpty()) {
            processedVal = Handyman.escapeCharacters(Handyman.capitalizeIf(inputString, capitalize), charsToEscape);
        } else {
            processedVal = "";
        }
        return processedVal;
    }

    /**
     * Processes the input LocalDate object to a formatted string based on year presence.
     *
     * @param date The LocalDate object to be processed.
     * @return The processed string representation of the date.
     */
    private String processVal(LocalDate date) {
        String processedVal;
        if (date != null) {
            if (date.getYear() == 0) {
                processedVal = date.format(DateTimeFormatter.ofPattern("MMdd"));
            } else {
                processedVal = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
        } else {
            processedVal = "";
        }
        return processedVal;
    }

    /**
     * Returns a string representation of the vCard object.
     * <p>
     * The string representation includes all properties of the vCard, such as identification details,
     * communication details, address details, organization details, and additional details.
     * Each property is formatted according to the vCard syntax.
     *
     * @return A string representation of the vCard object.
     */
    @Override
    public String toString() {
        return capitalize ? core_vCard.toString().toUpperCase() : core_vCard.toString();
    }
}
