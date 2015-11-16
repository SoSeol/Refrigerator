package Problem_Domain;

public abstract class GenericList
{
	abstract public String showList();
	abstract protected UpdateMessage createUpdateMessage(UpdateMessageType t, String newItemName, String operatorName); 
}
