import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.*;


public class Assignment1 {
    private static List<List<String>> data;

    public static void main(String args[]) throws IndexOutOfBoundsException, IOException {
        String input;
        Scanner scan = new Scanner(System.in);

        System.out.println("My booklist library manager- by Chng Zhi Xiang.\nWelcome to the library. How may i help you?\n" +
                "\n(Note: Type in the first letter of each option to select from the Menu)");
        do {
            a1ReadFile file = new a1ReadFile();
            file.openFile();
            data = file.readFile(); //so now the booklist that carried out from readFile is now known as data
            System.out.println("\nMy Library Menu: \n" + "R - Show books that i have not read\n" +
                    "C - Show books that i have read\n" + "A - Add a new book\n" + "M - Mark a book as read\n" + "Q - Quit");
            input = scan.next().toUpperCase();
            if (!input.equals("R") && !input.equals("C") && !input.equals("A") && !input.equals("M") && !input.equals("Q")) {
                System.out.println("Error: Please type in the first letter of each option to select from the Menu)");
            } else if (input.equalsIgnoreCase("R")) {
                unreadBooks(data);
            } else if (input.equalsIgnoreCase("C")) {
                completeBooks(data);
            } else if (input.equalsIgnoreCase("A")) {
                addBooks(data);
            } else if (input.equalsIgnoreCase("M")) {
                markBooks(data);
            }
        } while (!input.equals("Q"));

        System.out.println("Thanks for using the library app, welcome again and have a nice day!\n" + "\nDay of the quote:");
        //file.closeFile();
        List<String> quotes = new ArrayList<>();
        quotes.add("'The journey of a thousand miles begins with one step. - Lao Tzu'");
        quotes.add("'In every walk with nature one receives far more than he seeks.' - John Muir");
        quotes.add("'Discipline is the bridge between goals and accomplishment.' - Jim Rohn");
        quotes.add("'The only true wisdom is in knowing you know nothing.' - Socrates");
        quotes.add("'It's the little details that are vital. Little things make big things happen.' - John Wooden");
        quotes.add("Knowledge is of no value unless you put it into practice.' - Anton Checkhov");
        quotes.add("There is no quote of the day for now, try again later!");
        Random randomQuotes = new Random();
        String random = quotes.get(randomQuotes.nextInt(quotes.size()));
        System.out.println(random);

    }


    public static void unreadBooks(List<List<String>> data) {

        System.out.println("Total of " + data.size() + " books found from the library.\n");
        System.out.println("Book/Books that not yet being read: ");
        int read = 0;
        for (List<String> each : data) {
            if (each.get(3).equalsIgnoreCase("r")) {
                System.out.println(each.get(0) + ", by " + each.get(1) + ".");
                read += 1;
            }
        }
        if (read == 0) {
            System.out.println("All books have been read/No books found from the library.");
        }
        //return data;
    }

    public static void completeBooks(List<List<String>> data) {

        System.out.println("Total of " + data.size() + " books found from the library.\n");
        System.out.println("Book/Books that had read: ");
        int read = 0;
        for (List<String> each : data) {
            if (each.get(3).equalsIgnoreCase("c")) {
                System.out.println(each.get(0) + ", by " + each.get(1) + ".");
                read += 1;
            }
        }
        if (read == 0) {
            System.out.println("All books have not yet been read/No books found from the library.");
        }
    }

    public static void addBooks(List<List<String>> data) throws IOException {
        int pages;
        String book;
        String author;
        Scanner input = new Scanner(System.in);
        System.out.println("Add book function selected. Please type in the information below:\n" + "Book name: ");
        book = input.nextLine();
        while (book.isEmpty()) {
            System.out.println("Input cannot be empty!\n" + "Book name: ");
            book = input.nextLine();
        }
        System.out.println("Author name :");
        author = input.nextLine();
        while (author.isEmpty()) {
            System.out.println("Input cannot be empty!\n" + "Author name: ");
            author = input.nextLine();
        }
        System.out.println("Pages:");
        while (true) {
            try {
                pages = input.nextInt();
                while (pages < 0) {
                    System.out.println("Pages cannot be negative!");
                    pages = input.nextInt();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!\n" + "Pages:");
                input.next();
            }
        }
        System.out.println(book + " (" + pages + "pages), " + "by " + author + ", added into the library booklist.");
        data.add(Collections.singletonList(String.format("%s, %s, %s, r", book, author, pages)));
        a1ReadFile.saveFile(data);

    }

    public static void markBooks(List<List<String>> data) throws UnsupportedOperationException{
        int num = 1;
        int count = 0;
        int mark = 0;
        int bookNum;
        Scanner input = new Scanner(System.in);
        for(List<String> x: data){
            if(x.get(3).equalsIgnoreCase("r")){
                mark = 1;
            }
        }
        if(mark==0){
            System.out.println("All books have been marked as read.");
        }
        System.out.println("Type the number of the book to mark as read: \n"+"No.");
        for(List<String> each:data) {
            if (each.get(3).equalsIgnoreCase("r")) {
                System.out.println(num + ". " + each.get(0));
                num += 1;
            }
        }
        bookNum = input.nextInt();
				
				try {
					FileWriter fw = new FileWriter("doc/books.csv");
					for(List<String> each :data){
						if (each.get(3).equals(bookNum)) {
							fw.append(each.get(0));
	            				fw.append(",");
	            				fw.append(each.get(1));
	            				fw.append(",");
	            				fw.append(each.get(2));
	            				fw.append(",");
						}
			            if(bookNum == count){
			            		fw.append("c");
			            		fw.append("\n");
			            } else {
			            		fw.append(each.get(3));
			            		fw.append("\n");
			            }
			            count++;
			            }
					fw.flush();
					fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				
				
			
    }
}




    







