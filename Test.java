给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

 

示例 1：

输入：["abcd","dcba","lls","s","sssll"]
输出：[[0,1],[1,0],[3,2],[2,4]] 
解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/palindrome-pairs
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res=new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(int i=0;i<words.length;i++){
            map.put(new StringBuilder(words[i]).reverse().toString(),i);
        }
        for(int i=0;i<words.length;i++){
            String word=words[i];
            for(int j=0;j<word.length();j++){
                if(isValid(word,j,word.length()-1)){
                    int index=map.getOrDefault(word.substring(0,j),-1);
                    if(index!=-1){
                        res.add(Arrays.asList(i,index));
                    }
                }
            }

            
            for(int j=0;j<word.length();j++){
                if(isValid(word,0,j)){
                    int index=map.getOrDefault(word.substring(j+1),-1);
                    if(index!=-1){
                        res.add(Arrays.asList(index,i));
                    }
                }
            }

            int index=map.getOrDefault(word,-1);
            if(index!=-1&&index!=i){
                res.add(Arrays.asList(i,index));
            }
        
        }

        
        return res;
    }
    private boolean isValid(String str,int left,int right){
        while(left<right&&str.charAt(left)==str.charAt(right)){
            left++;
            right--;
        }
        return left>=right;
    }
}