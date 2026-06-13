package model;

public class MessageDTO {
    public String senderName;
    public String senderInitials;
    public String preview;
    public String time;
    public boolean read = false;

    public MessageDTO(Message message) {
        String name = message.getFromUser().getName();
        this.senderName = name;
        this.senderInitials = initials(name);
        this.preview = message.getMessage();
        this.time = "just now";
        this.read = false;
    }

    private String initials(String name) {
        String[] parts = name.trim().split(" ");
        if (parts.length > 2) {
            return ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
        }
        return name.substring(0, Math.min(2, name.length())).toUpperCase();
    }
}
