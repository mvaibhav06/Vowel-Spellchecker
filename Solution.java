class Solution {
    public boolean equal(String query, String word){
        if(query.length() != word.length()){
            return false;
        }
        List<Character> vowels = List.of('a','e','i','o','u');
        String s = "";
        
        
        for(int i=0; i<query.length(); i++){
            char ch = query.charAt(i);
            char res = word.charAt(i);
            if(vowels.contains(ch)){
                if(vowels.contains(res)){
                    continue;
                }else{
                    return false;
                }
            }else{
                if(ch != res){
                    return false;
                }
            }
        }

        return true;
    }

    public String modify(String s){
        List<Character> vowels = List.of('a','e','i','o','u');
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!vowels.contains(ch)){
                sb.append(ch);
            }else{
                sb.append('*');
            }
        }
        return sb.toString();
    }

    public String[] spellchecker(String[] wordList, String[] queries) {

        int wordLength = wordList.length;
        int queryLength = queries.length;
        
        String[] out = new String[queryLength];

        Map<String, List<String>> words = new LinkedHashMap<>();
        Map<String, String> modifiedWords = new LinkedHashMap<>();

        for(int i=0; i<wordLength; i++){
            String s = wordList[i];
            s = s.toLowerCase();

            List<String> temp = new ArrayList<>();
            if(words.containsKey(s)){
                temp = words.get(s);
            }
            temp.add(wordList[i]);
            words.put(s, temp);
        }

        for(String key : words.keySet()){
            String s1 = modify(key);
            if(!modifiedWords.containsKey(s1)){
                modifiedWords.put(s1, key);
            }
            
        }

        for(int i=0; i<queryLength; i++){
            String s = queries[i];
            s = s.toLowerCase();
            if(words.containsKey(s)){
                List<String> temp = words.get(s);
                if(temp.contains(queries[i])){
                    out[i] = queries[i];
                }else{
                    out[i] = temp.get(0);
                }
            }else{
                String s2 = modify(s);
                if(modifiedWords.containsKey(s2)){
                    out[i] = words.get(modifiedWords.get(s2)).get(0);
                }else{
                    out[i] = "";
                }
            }
        }
        return out;
    }
}
