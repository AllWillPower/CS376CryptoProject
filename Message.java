import java.util.*;

public class Message
{
    public static void main(String[] args)
    {
        String[] message = {"Go until jurong point, crazy.. Available only in bugis n great world la e buffet... Cine there got amore wat...",
                "Ok lar... Joking wif u oni...",
                "Free entry in 2 a wkly comp to win FA Cup final tkts 21st May 2005. Text FA to 87121 to receive entry question(std txt rate)T&C's apply 08452810075over18's",
                "U dun say so early hor... U c already then say...",
                "Nah I don't think he goes to usf, he lives around here though",
        "FreeMsg Hey there darling it's been 3 week's now and no word back! I'd like some fun you up for it still? Tb ok! XxX std chgs to send, å£1.50 to rcv",
                "Even my brother is not like to speak with me. They treat me like aids patent.",
                "As per your request 'Melle Melle (Oru Minnaminunginte Nurungu Vettam)' has been set as your callertune for all Callers. Press *9 to copy your friends Callertune",
                "WINNER!! As a valued network customer you have been selected to receivea å£900 prize reward! To claim call 09061701461. Claim code KL341. Valid 12 hours only.",
                "Had your mobile 11 months or more? U R entitled to Update to the latest colour mobiles with camera for Free! Call The Mobile Update Co FREE on 08002986030",
                "I'm gonna be home soon and i don't want to talk about this stuff anymore tonight, k? I've cried enough today.",
                "SIX chances to win CASH! From 100 to 20,000 pounds txt> CSH11 and send to 87575. Cost 150p/day, 6days, 16+ TsandCs apply Reply HL 4 info"};
        clean(message);

        for(int i = 0; i < message.length; i++)
        {
            System.out.println(message[i]);
        }
    }
    public static void clean(String[] message) // This makes all of the strings in the array lowercase and alphabetical.
    {
        char emptyC = '2';
        for(int i = 0; i < message.length; i++)
        {
            message[i] = message[i].toLowerCase(Locale.ROOT);
            char[] charArray = message[i].toCharArray();
            for(int j = 0; j < charArray.length; j++)
            {
               char ch = charArray[j];
                if(!(ch >= 'a' && ch <= 'z') && ch != ' ')
                {
                    message[i] = message[i].replace(message[i].charAt(j), emptyC);
                }
            }
            message[i] = message[i].replaceAll("2", "");
        }
    }
}
