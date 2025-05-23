package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WiseSaying {
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        List<WiseSaying> wiseSayings = new ArrayList<>();

        int lastId = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                lastId++;
                wiseSayings.add(new WiseSaying(lastId, content, author));
                System.out.printf("%d번 명언이 등록되었습니다.\n", lastId);
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wiseSayings.size() - 1; i >= 0; i--) {
                    WiseSaying ws = wiseSayings.get(i);
                    System.out.printf("%d / %s / %s\n", ws.id, ws.author, ws.content);
                }

            } else if (cmd.startsWith("삭제?id=")) {
                int idToDelete = Integer.parseInt(cmd.substring("삭제?id=".length()));
                boolean found = false;

                for (int i = 0; i < wiseSayings.size(); i++) {
                    if (wiseSayings.get(i).id == idToDelete) {
                        wiseSayings.remove(i);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", idToDelete);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", idToDelete);
                }

            } else if (cmd.startsWith("수정?id=")) {
                int idToEdit = Integer.parseInt(cmd.substring("수정?id=".length()));
                boolean found = false;

                for (WiseSaying ws : wiseSayings) {
                    if (ws.id == idToEdit) {
                        found = true;
                        System.out.printf("명언(기존) : %s\n", ws.content);
                        System.out.print("명언 : ");
                        String newContent = scanner.nextLine().trim();

                        System.out.printf("작가(기존) : %s\n", ws.author);
                        System.out.print("작가 : ");
                        String newAuthor = scanner.nextLine().trim();

                        ws.content = newContent;
                        ws.author = newAuthor;

                        break;
                    }
                }

                if (!found) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", idToEdit);
                }
            }
        }

        scanner.close();
    }
}
