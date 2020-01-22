package com.leetcode.hard;

import java.util.*;

// https://leetcode.com/problems/similar-string-groups/
public class SimilarStringGroups {
    /*
        Idea:
        - Idea is to think about each string as a node in the graph. 2 nodes would be connected if those are equal. Edge would be undirected since both can be reached from each other.
        - Also satisfies the constriant that A -> B -> C, where  A and C even though not equal can still be in the same group.
        - After that the problem is reduced to finding connected components in the graph.
        -
    */

    private Map<Integer, List<Integer>> adjList;

    private int[] uf;

    public int numSimilarGroups(String[] A) {

        if(A == null || A.length == 0) return 0;
        int cc = 0;
        uf = new int[A.length];
        for(int i=0; i < A.length;i++){
            uf[i] = i;
        }
        formGraph(A);
        Set<Integer> unique= new HashSet<>();
        for(int i=0; i < A.length;i++)
            unique.add(uf[i]);
        return unique.size();
    }

    public int numSimilarGroups1(String[] A) {

        if(A == null || A.length == 0) return 0;
        int cc = 0;
        uf = new int[A.length];
        for(int i=0; i < A.length;i++){
            uf[i] = i;
        }
        formGraph(A);
        Set<Integer> unique= new HashSet<>();
        for(int i=0; i < A.length;i++)
            unique.add(root(i));
        return unique.size();
    }

    private void formGraph(String[] A){
        int n = A.length;
        int w = A[0].length();
        if(n < w*w){
            for(int i=0; i < A.length; i++){
                for(int j= i+1; j < A.length; j++){
                    if(equal(A[i].toCharArray(), A[j].toCharArray())){
                        union(i, j);
                    }
                }
            }
        }
        else{
            Map<String, List<Integer>> buckets = new HashMap<>();
            for(int i=0; i < n; i++){
                char[] arr = A[i].toCharArray();
                for(int x=0; x < arr.length; x++){
                    for(int y=x+1;y < arr.length; y++){
                        swap(arr, x, y);
                        StringBuilder sb = new StringBuilder();
                        for(char c: arr)
                            sb.append(c);
                        buckets.computeIfAbsent(sb.toString(), z -> new ArrayList<>()).add(i);
                        swap(arr, y, x);
                    }
                }
            }
            for(int i=0; i < A.length ; i++){
                if(buckets.containsKey(A[i]))
                    for(int neigh: buckets.get(A[i])){
                        union(neigh,i);
                    }
            }
        }
    }

    private void swap(char[] arr, int a, int b){
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private void union(int a, int b){
        // Quick Find.
        int val = uf[a];
        for(int i=0; i < uf.length; i++){
            if(uf[i] == val)
                uf[i] = uf[b];
        }
        // uf[root(a)] = uf[root(b)]; // Quick Union Implementation. Lets try the quick Find implementation. This took 5925 ms and 47.4 Mbs.
    }

    private int root(int a){
        while(uf[a] != a)
            a = uf[a];
        return a;
    }



    public int numSimilarGroups2(String[] A) {

        if(A == null || A.length == 0) return 0;
        int cc = 0;
        boolean[] visited = new boolean[A.length];
        adjList = new HashMap<>();
        formGraphDFS(A);
        for(int i=0; i < A.length ;i++){
            if(!visited[i]){
                dfs(visited,i);
                cc++;
            }
        }
        return cc;
    }

    private void dfs(boolean[] visited ,int v){
        visited[v] = true;
        for(Integer w : adjList.getOrDefault(v, new ArrayList<>())){
            if(!visited[w]){
                dfs(visited, w);
            }
        }
    }


    private void formGraphDFS(String[] A){
        for(int i=0; i < A.length; i++){
            for(int j= i+1; j < A.length; j++){
                if(equal(A[i].toCharArray(), A[j].toCharArray())){
                    adjList.putIfAbsent(i, new ArrayList<>());
                    adjList.get(i).add(j);
                    adjList.putIfAbsent(j, new ArrayList<>());
                    adjList.get(j).add(i);
                }
            }
        }
    }


    // Since the strings are anagrams, you don't need to a separate set to keep track of which characters are different amongst the set and comparing that. They would just be out of position.
    private boolean equal(char[] a, char[] b){
        int diff = 0;
        for(int i=0; i < a.length ; i++){
            if(a[i] != b[i]){
                diff++;
                if(diff >2) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SimilarStringGroups s1 = new SimilarStringGroups();
        String[] A = {"vklldovi","lvdiklov","dlkvoilv","likolvvd","ldlvviko","kvdivlol","vlidklov","iovlkdvl","kvlvdiol","dvkillov","dvoklliv","kilvvold","ldliovvk","vldokvil","loikvvdl","illvodvk","vovlkidl","iklvlodv","vdvlkilo","llvkivdo","vklvdilo","oivkvldl","odlvikvl","vlokivld","vvkloild","vlkdlovi","klolvdiv","viovklld","klivdlov","odvlkliv","loidlkvv","llvodkvi","klvlivod","iokvlldv","oidkvlvl","llodkvvi","vldolivk","lvolvdki","ldoklvvi","lvokvild","lvilkdvo","vdovklil","ivkldvol","dikvvoll","ikovvdll","kvdliolv","odkllivv","lvvldkoi","dkilovvl","viodkllv","ldkvovli","illokvvd","dlvkliov","klivoldv","lvlvkido","kvlviodl","klvlvdio","ovlldivk","lkdviovl","dilkvlvo","lovlkvdi","ovkdlivl","olkilvvd","okvlivld","lidvvlok","iokvldlv","vlolvdik","ivlvdklo","lvvlkodi","kovdilvl","lilvvodk","ldvolkiv","vlkidolv","vidkllov","dlvoilvk","vklidolv","kdolivvl","ldivvlko","vvdlliok","vlviokld","dlvvloki","vlivkdol","vlildvko","kllivovd","dlilovkv","lvdilkvo","ildvlokv","odvllivk","odlvkilv","ldvkvoil","dvillkov","illkovvd","llodvikv","ivkollvd","kvildvlo","loldikvv","dvolivlk","ovilvlkd","dolilvvk","llovkvid","ildvkovl","idlvvlok","llvoikdv","dvlilkvo","lkdlivvo","vlovdikl","kiollvdv","lkildvvo","lvkovlid","dkoillvv","liolvvkd","vklvloid","ivlvokld","ldvloivk","klldvovi","dviolvkl","ikollvvd","lvlkodiv","kvdvilol","lolkidvv","llkdvoiv","ldvkvlio","dlvolvik","dlolivkv","vodlvlik","okvvdlli","lklviovd","vovdllki","dovlklvi","lvkioldv","vidvllok","vviklodl","klvliovd","olkvlidv","ovdlvlik","vkldvilo","kovdivll","lklidovv","ikdovlvl","kvvlldio","llvoidkv","dvviollk","dvolvikl","ilvdlkvo","diklvvol","lvkldvio","kidolvlv","volivldk","llkdoivv","idovlvlk","kvlliodv","vlkodlvi","dklliovv","odliklvv","dlioklvv","ldvlikvo","dvloivlk","kvdvillo","ikvovdll","kodlivlv","llkviodv","odlvivlk","vdlkolvi","ldkvvlio","liovdvlk","olkvdvli","lviovdkl","lldivovk","iklldvov","ildvvlko","odkvivll","llodikvv","dovllkvi","odlvlvik","lvvodlki","okvidllv","dovilklv","vodlivlk","klvidovl","vildvolk","ldvvkilo","lvlokvid","vokilvld","dolvkilv","vvolkdil","vlvlkodi","vkvodlli","lvdoklvi","llkodviv","vdkllvio","dloklvvi","ldvvkoli","liovvkld","kidvovll","ldokilvv","lvdolvki","lloidkvv","ldloikvv","ikovvlld","dvoillvk","klvvilod","lkilvvdo","lvvlidko","livkldvo","dlolvvki","viklodvl","vdollkiv","vdolkvil","lvlvoikd","lviovkdl","ldlikvvo","kildovvl","idlklvvo","volidklv","okdlilvv","kvoldilv","voillvkd","vldiolvk","ovdvllki","kdvvoill","illvovkd","dokillvv","ovikvldl","vvollidk","lovilkdv","vklvldio","lvkvodil","llvvdkoi","vlvdolki","vkvoldli","llovvidk","villkdov","kdilvlov","dvlokivl","llvidvko","vlvokidl","klvdvoli","llkvdoiv","divlklvo","vldlkvio","vldlokvi","ilvkdolv","vlkivold","kvvdliol","lildvovk","olvlidkv","vvdiollk","lvlivodk","vliodvkl","lkolivvd","ldvkivol","lvoilvkd","vdvlokli","lvivdkol","oivlvkdl","vlkdovil","vvklliod","dvolvkli","ilkvdvol","volilkdv","lvvkldoi","ikllodvv","oildvlvk","vkilvold","kvivdllo","dlklviov","ildkolvv","ioldlvvk","vvikdoll","vloilkdv","klvlviod","dvlokvli","kvidolvl","kvliovld","ldlvkiov","olivlvdk","ivdkovll","oldlkvvi","dlilvvok","ovvkdlil","ovllvdki","lvlkoidv","vvkoildl","dklvvloi","okvldvli","lkvivold","kodlvvil","dvlvolki","vilkvold","kvivodll","dvlklivo","lolivvkd","idlvklov","llvdviko","vdoillvk","ldvilvko","oldlvkvi","dlkvvlio","vlkoilvd","ovldklvi","dlkiovvl","llvviodk","lvvikldo","dvklivlo","ilokvdvl","ildklovv","dilvvolk","olklivvd","vlvkidlo","olvlikdv","ovikllvd","dkvoivll","lvdlkiov","ilvkvdol","ollikvvd","diokllvv","vvkoilld","ivdlvlko","lvikldvo","villkodv","vvdlkiol","likvlovd","odvvilkl","kolidlvv","ivkdvllo","lildvkvo","kidvlolv","kodilvvl","odvklliv","dklovivl","dvoilkvl","liodvklv","okvilvdl","vklovild","lkoldvvi","ovilvldk","lloikvdv","ldvlivko","vildlkvo","vikldlvo","lvikvodl","vivolldk","lidovlkv","kldovvil","vikvodll","vlilovkd","lidlvvko","kiovdvll","vollvdik","ikllvodv","ivldoklv","ldvivkol","ldvvkoil","livdvolk","odvvlkli","koldvliv","kvvoildl","vdliklov","vdoikvll","odlvvikl","vilvdlok","lvlkidov","vliodvlk","llovvikd","oldivkvl","llvdikvo","vdllivko","lvolivdk","odkvlilv","dlkvliov","kvlovldi","lvoidlkv","olvvkidl","lvkoidlv","ovkdilvl","ovivlkld","iollvdkv","lildkvvo","lvdvkiol","ldiovlkv","kdiolvlv","dolvkvli","iodlvvkl","vvolildk","volvlidk","dvlkliov","ilvkdvlo","lklvvido","idlkvovl","idllvokv","lodvvlki","dvkvioll","ivlovkdl","vvodklil","kilvldvo","odilkvvl","ikdlovvl","vdkvilol","vvlliodk","kdlovvil","ldivkvol","idvlklvo","lvolvikd","vkdoivll","vvdkolil","ivdlklvo","lvidklov","vlovkild","kvdlolvi","kvovldil","likvdolv","ovlidlvk","lviovldk","lvlkvido","ikvdllov","llovdikv","ovdvlikl","vvlklido","vlkodvil","lkdvvloi","vlklvdio","odlvilvk","ldkvliov","llkivovd","ivkvlold","lidklvov","idlkvvol","klivovld","kvloilvd","llovkidv","llivvkod","oivllkdv","odkllviv","ovldlivk","ivdoklvl","volivkdl","ivllvkdo","klovvild","diovlvlk","vkliovld","ivodklvl","olkvdilv","olvklivd","vvlilokd","ldvkivlo","livvdklo","kovvlild","vdlklivo","vldlkovi","dolvlivk","vklvdoli","iklvvdlo","lodilkvv","voiklvld","lildvvko","lviodvlk","vlilvdko","vkioldvl","odivlklv","kildvovl","kvvlildo","odlvklvi","dvllovik","lvklidvo","lvoldvik","lkvovlid","vklovlid","vlokidvl","llokdvvi","olvvlkid","odikllvv","lvvliodk","olivlvkd","kviodlvl","lvldvoik","kviodvll","kilvodvl","vkivlold","kldiovvl","lvklodiv","kliovvdl","dlliovkv","odvilvkl","lvldviko","dokilvvl","lvliovkd","ivklodlv","vlkoivld","vdkloivl","doilvvkl","oidllvvk","oidvllkv","dollikvv","kvillovd","odvlivkl","olkvdivl","voilldvk","lldokvvi","lvvikold","ivvloldk","ovdivlkl","idvvlokl","kvioldvl","vvkidlol","lvdkovli","oillvvkd","lodvlvki","vodllkvi","dvovlkil","kliovvld","dvlvloik","vklodilv","vlolidkv","vlidklvo","ivvodlkl","okivdllv","lodklvvi","kviolvdl","ovkivldl","ldkvlvoi","kdlvlvoi","ikovvldl","vvldloki","vlkvolid","ikvvoldl","divkllov","villvdko","diovkvll","vvdoilkl","vlidvlko","vvklldio","lkvidolv","ldivlvko","idkvlolv","odlikvvl","idovklvl","dlklvoiv","dvlloivk","dovvlilk","divkollv","vvikllod","vilvdkol","vvodlikl","vlvdlkio","oklvldvi","lkdivolv","vlldkivo","killodvv","iolvkvdl","ovllivkd","kodvivll","vkvioldl","vklvdiol","vllviodk","ivvldklo","dlvolvki","ldoivvkl","lodvkliv","iolvvldk","ikdllvvo","ilvlvdok","vkilovld","lkolvdvi","ilokvldv","vlkvilod","ovvdlikl","ikldovvl","llkovivd","lvkdilov","lovikdlv","dvlolvki","voldilvk","lolivvdk","okvilvld","vdvollki","dlivolkv","vvdkliol","kvdovlil","odivkllv","vldikvol","kdlolvvi","vkdilovl","livdlovk","olvlivdk","voikllvd","vllokivd","vvdkilol","iklovdlv","vdolvikl","idvvollk","kdovvlil","ovdvlkil","dolklvvi","ldvvlkoi","lvkolvdi","vlkvoidl","vlivokdl","ivlvdlok","ldivlokv","divvollk","dvllkoiv","klvoilvd","ikdovvll","vldvoilk","lkdlvvio","kollvdiv","ldliovkv","lodvvkli","livkodlv","viodvlkl","illdovvk","lviokvld","kdlvlovi","kdoivlvl","iodvvkll","ovlvkdil","okvdilvl","vkodillv","vlvoilkd","vdovlkli","oilklvvd","vioklvdl","klvdlvoi","vkvloldi","okldilvv","lvoivkld","lkvovild","lkldivvo","ovdlvlki","oklvvidl","liokldvv","kldvlivo","vovdlkli","ikdvlolv","ivklldov","ildvlkov","kdivolvl","lvkoivld","lkvvoild","vkdloilv","ivlldkvo","vvodillk","lvklovdi","lovilvkd","lvloikvd","lovlvidk","kivodvll","lvovkidl","lvovlkid","klvvidlo","kldvolvi","vdllkvio","vivkldlo","lvviklod","ldvivlok","iolldvkv","dkiovllv","lidvkvol","olidlvkv","vklvildo","llvidovk","oildlvkv","dovlvlik","ilvdlovk","vdlokliv","okillvvd","voldkvli","vdvkoill","lklovivd","liklvodv","klvlodvi","dlkvovil","vldloivk","dvlioklv","lodviklv","dvlkoivl","lovklidv","dllivovk","vdlvoikl","lovvdkli","dilvolkv","lkldvvio","lkvolvdi","kvoilvdl","dvlkiovl","kllivvdo","ldlvikov","lvikdvol","dllkivvo","llkvoidv","lkvlovdi","koidlvvl","ovdllvki","dklovvil","livkdvlo","dlvilvok","ilvovldk","lkvvolid","ivvldlko","dlvolkvi","kolilvvd","llkovdiv","vkviolld","vdollvki","dvllviok","vlodklvi","ollikvdv","kdvlilvo","dollvkiv","ldkolvvi","kdillovv","lvvklido","vklilvdo","vlioldkv","lioldvvk","vldilovk","dklvlivo","lovkvild","oildlkvv","vllovkdi","vdvkolil","ivdovlkl","vlldikvo","ilvkvdlo","idovlkvl","lvokldvi","lvivkdol","lvvidkol","vlldviko","ovlvikld","oivvllkd","vdlivlko","vdiklvlo","vliolvdk","oklivvld","vovdllik","kolvivld","lkvivlod","llvkiodv","vlkdvilo","vkdolvil","klovdvli","ldklviov","ldlikvov","lvvlkido","vidlkovl","kolivvld","vlolivkd","dvllivok","lolikdvv","lvdvikol","vdvilklo","vilvokdl","oivkllvd","lkivldvo","vvldkiol","loidlvvk","vlkildov","lkivldov","dloivlkv","lvkovldi","divkllvo","vllvokid","lvidovkl","kvllodiv","vdvolkil","vokdvill","iovvkldl","oilvldkv","lvvodkil","lkvdiolv","lkvlviod","kdlolviv","vllikvod","kdlovvli","voilkdvl","kvdovlli","volvidlk","vlidlvok","llvdkivo","okidvlvl","lvloivkd","vlovlidk","iovdlklv","koldivlv","lvdklivo","kvvloild","lidkvvlo","likvvldo","vovdklli","okdvlvli","odklivlv","ivlvokdl","vivkllod","vlvoikdl","livlodkv","kvdvolli","vkdvlloi","dovkvlil","lkdlvivo","klvivldo","dvivolkl","kdolvilv","vvolkidl","kvdvloil","lodvkvli","dlkviolv","vkvoidll","vokilldv","lkivodvl","liokvvld","lvlkdivo","vdllikvo","kldilvvo","vlviklod","ldvkolvi","lkvildvo","odllivkv","ldkiovlv","idvklvlo","kvlvldio","lkodlvvi","lvkdvoil","ldokvlvi","ldlkvvio","kdlvoliv","lvivlokd","llvvdoik","likvdvlo","ildvovlk","oivkvdll","dlolvvik","iolkvdlv","ovklvdil","lvdovkil","lolvidvk","ilvdlvko","dlkvviol","diklvlov","kvdlvloi","olkdvvil","kildvlov","vlivldko","kdilvvlo","lvivoldk","vvkilold","kldovvli","idklvolv","ldlivovk","lilkvdov","vdllikov","ivlokvld","lvkdlvoi","ivdokvll","ldvkilov","dlvlkoiv","kiovlvld","dvlolvik","kiolvvld","klviodvl","okilldvv","kdovlliv","lvokivld","kldlviov","kvlvoldi","lvvkdilo","ldlovikv","vllvoikd","vlkidlvo","ildklvov","lvdlvoik","oivdvkll","ldikvlov","vlkdvoli","dilklvvo","oklvvdil","lviovlkd","ilklvodv","ikldlvvo","divlvklo","lovvlikd","dovvllki","ovldkivl","vodivlkl","killovvd","ivlvkdlo","ldokvivl","vikodvll","dvlvkoli","ilvolkvd","llkodvvi","kvllovid","vkdivoll","idolvklv","ilokdlvv","oidllvkv","idllkvov","ldovkivl","vollkdvi","vlldkovi","illvkvdo","lilvvdok","lolvvkdi","vlvolikd","vklivldo","ildvkolv","dlvovlik","vidvkllo","ldovlvik","dvlikolv","viodklvl","oivvldkl","kilolvvd","iovvlldk","kvlodilv","livodlvk","voillvdk","ilvdvokl","lidvvlko","ivoldklv","dillvokv","kvilvodl","odlilvkv","ivoldlvk","kilovldv","kodvllvi","kvlliovd","kodvlvil","vllvdiko","iovkllvd","loklvvdi","dvkovlli","dvvillok","doilvlvk","ilvldvko","lkvliovd","ovdllkiv","vdoklliv","kildovlv","livlvkod","kldviolv","llokvdvi","vlkiovdl","lvkiodlv","dlviovlk","vldilvok","dvllokvi","ilvodlvk","dlvlikvo","lvdkilvo","kvdllovi","kivlvdlo","kllvodiv","idllovkv","kvliodlv","ovlkivld","dlvovlki","oilvkvdl","lokvivdl","ovlkivdl","dvlkloiv","llviovkd","vkolvdil","ovkvllid","vlivdolk","lidkvlov","ovkidlvl","vdokvill","olvvlikd","dvlviokl","dvolkvli","ovidlklv","dlvovkil","lodlikvv","ovkvidll","dkivvlol","dolvlvik","llkoidvv","lvkilovd","vvildolk","lidvlovk","vkvdillo","kovlivld","vvliodkl","klvovild","dllkovvi","vvkldiol","idllvvok","iklovvld","vilovdkl","ldolvivk","vvllikdo","ovldlvik","lokdvvli","dvvollki","iovlvdkl","lvklvdoi","idvvklol","lovdklvi","dvlovlik","lvdokivl","divlklov","lilkovvd","llkivvdo","vkivdlol","kodvillv","livkodvl","ollvvkdi","ilovldvk","lvldvoki","kllvvdio","klldvoiv","lvlodkvi","lvidolkv","ilkodlvv","olkidvlv","lkdvvoil","vdlvlkio","idvkllvo","kvdovill","vllkodvi","dilovlvk","ilvlkdvo","ovvllkid","kilvdolv","kvdilvol","vkvloidl","ovkildlv","volkvlid","olikdvlv","dlvklovi","llidvkov","livlvokd","idovvkll","dvlilvko","vviokldl","lkviolvd","ovdklilv","ivvkldlo","vdlliokv","ovldkilv","lilkvvdo","vvikdlol","vvlkldoi","oviklvld","diolkvlv","divlkvlo","llivvdok","lvldkvio","lvdolivk","lvioldkv","vvodklli","lovikdvl","odvklivl","vkvolldi","oilkvvdl","dilkvvlo","llkiodvv","lokldvvi","lklvdovi","klvdlvio","lilvkdov","vvodlkil","lvilokdv","lvkdliov","lovlivkd","dlvkviol","kvlioldv","llkiovvd","okdivvll","kdlovliv","oiklvvdl","ldvlkvoi","oivvkldl","ldkvoivl","lliovdvk","vkodlvil","dlvlivko","idvlvlko","lidlvvok","ilvvdkol","kilodvlv","lvdlikvo","vdilkvlo","kvloldiv","divllkvo","ldklvovi","odvikvll","ldlvkvio","vlivkdlo","vklvidlo","klivvldo","loilvkdv","ovivdllk","odivllkv","lvokvidl","kvllvdio","ovldlkvi","oikdvvll","vvoldilk","dklovilv","ivllodkv","vlvliodk","lkldviov","vollkdiv","ovildlvk","vilkvodl","vovilkld","oldvvkli","vvdlkilo","okvidlvl","ldokvilv","ollvkdiv","vllidkvo","livkdlvo","ovklldiv","vldvliok","vivllkod","vllkoidv","kdoillvv","dvklilvo","dklvlovi","lidklvvo","vkdolvli","lviklodv","dlolvivk","klilodvv","klivvlod","vkvdllio","lvkidovl","vvdolkil","ldokvvil","ldivvolk","ldiklovv","ovildvlk","llvvokdi","vlliodkv","voldlivk","ildvvkol","odkvlliv","ilvvkdlo","klvvoild","dlolvkiv","vidlolkv","lodivvkl","vdovikll","loldvivk","lvvkodil","ivdlvolk","llvkivod","oldkvivl","vokivdll","lkovidvl","kdvvloli","lkodvlvi","ikvlvlod","lvdkivol","vllkoivd","kvlvdoli","kdolvvil","vlodilvk","ldlvviok","vkldvoli","odlikvlv","iklvvlod","dvviklol","kdovvill","dklvivol","llvkdvio","vloilvdk","vdoilklv","ikvldlov","lkvloidv","lldvvkoi","ilvkodlv","vokvldli","liolkvdv","kovildvl","oikvlldv","ilvvolkd","vokvlldi","llvivdok","ivvklold","olvdlivk","llvikdvo","vlivdokl","llkvvdoi","kvvdolli","iodkvvll","idklvvlo","voidkllv","ilvdvlko","vlikdvol","odllvvik","lilovvdk","vviokdll","ilkvlvdo","kloilvvd","voiklvdl","dlkovvli","vvklildo","kdivvllo","vdlkloiv","vvllodik","vilkldvo","villdkvo","lokivdlv","lkidvolv","lvdkvloi","kvldvoil","vkvoildl","oikvlvld","kdivlovl","ikdllvov","lkidvvlo","lvdlviok","lioklvvd","llodvkiv","olvkivld","odvlilkv","lvikoldv","vdlilovk","ivldkolv","vldlovki","kvvilodl","lvkdoivl","iodvlklv","lidvlvko","kdvliolv","okvvlild","ldkvvloi","ilokldvv","vlvloikd","violdklv","lvlvkoid","dvkiollv","ldkvovil","olvvdikl","vollivkd","dlklvovi","ovikvdll","lodlkvvi","vidokvll","lilvkdvo","lvdvkoil","olvkdilv","vkliovdl","vldivolk","vlidolkv","volkdvli","ilvodlkv","lldviovk","vdklovil","vdlkvloi","lodlkivv","kdvovlil","klviolvd","ovdkillv","dlvlovik","llodivvk","dovvilkl","lvkiolvd","ivdvlolk","odivvkll","vlovdkli","ivdklvol","livodlkv","vidlkvol","vodlvikl","koidvlvl","ovdlkivl","dvvklilo","klvldovi","vloldvik","ilvolvdk","volkdlvi","vloldkvi","vvldkloi","lvkovild","lvlovkid","llvvdoki","lkvidvol","vildklvo","idlvlkov","volvdkli","kvlolidv","dokivlvl","lvdkiovl","kivlolvd","okivldlv","vidlvolk","olvkvdli","lvkvilod","dilvlvko","vodvlkli","vkvdlilo","lvdlovki","ovdklliv","ldvvkloi","vkovidll","illvdvko","lkdvvoli","lldkivvo","lkdlviov","vodilklv","vdklilvo","ldvikvol","liokvlvd","ivllvdko","dlvikolv","lvlkiovd","lovdiklv","kvdllvio","lvioklvd","ilvvdolk","dkvvlilo","lkvivdol","iklvvdol","ildvovkl","klvolidv","vvdolilk","kvdlvoil","kolvidvl","vkdoilvl","vdikolvl","ldioklvv","ovvlldki","vlkiodlv","okllivvd","lvikvldo","ovikldlv","lvdkvlio","lvdkivlo","kliovlvd","illkodvv","llvoidvk","loklivdv","okdllviv","dvlvoikl","llokidvv","lvldvkoi","kdvolvli","ldolvvki","vkiolvdl","klvdolvi","livklvod","olvvidkl","ovidvlkl","vldkolvi","lovvkldi","vokdilvl","likdvlvo","ovlvilkd","lkoildvv","vllovkid","kidovlvl","vvlkldio","ildvokvl","vvkdloli","lvoidvkl","vokvidll","vkdvilol","lkvdvoli","dkillvvo","kdvillvo","ivdklvlo","dlkvilov","vodvklli","vkvilold","ldvvloki","likdlovv","likvdvol","vldilvko","llvovdki","llvvikdo","dvolvkil","dikolvvl","ovkldivl","iovllvkd","vlikolvd","vvdollik","lokivvdl","odivklvl","ldvolivk","lvvidlok","lkovldvi","kvllvdoi","vdvolkli","llkovvid","vloivdkl","vlvkoidl","ldvvolik","idokvlvl","iovlklvd","vlkvidlo","ivvdokll","lklvidov","llvidokv","ovidllvk","olikldvv","viovdllk","vvldoilk","dllivkvo","lkovvidl","dlivkolv","lodlvivk","lildvvok","idvkvoll","ilvlovdk","oikvlvdl","vdllviko","vllkivdo","kvloivdl","lvlovidk","vkvldoil","lvkovdli","vlloivkd","vdllovik","dovvikll","iodlkvvl","kvvilold","lvlovikd","vivkldol","vlivolkd","oikvdvll","kvoidllv","klolvdvi","ldkvilvo","ildvlvko","ollvivkd","iokvvdll","lilvdovk","vviolkld","olkivvdl","dlvlviok","ovllikvd","vlolivdk","lvlkvdio","kllodvvi","loldvikv","vkvdliol","kivldvlo","vkvlildo","klldvvio","dvoilvlk","kodlivvl","lvdvkilo","vlkvliod","lvovkdil","lokdvilv","vlidkvlo","ovklidlv","vvklodil","vvilklod","kdvoilvl","lilovvkd","vdkvlloi","dolkvlvi","vollvikd","ivvldokl","kivlodvl","okllvivd","ldovlvki","iklldovv","dovivllk","vlivlokd","odklvliv","kdvlovli","ikvldlvo","illvvkdo","idvlolkv","lvvidolk","vvkldilo","ovivdkll","iklolvdv","idokllvv","vvdilolk","lkvodvli","kivlovld","liovlvkd","vvioldkl","diovvlkl","ldlkvvoi","vidllvok","vkdllovi","kiodllvv","vlvlkdoi","ovllvdik","vdklvilo","lklivdvo","kdovlilv","lvldkovi","lviolvkd","llvvkodi","kovvdlli","ilvlodvk","ivlkovld","kvldlovi","vdklvlio","olkvilvd","voklidvl","dollkviv","voikdlvl","dllkvovi","voilldkv","ilvokvld","oviklvdl","vdvoklil","kdiovlvl","vdkoivll","iloklvvd","lliodvvk","kvoilvld","dlklvvoi","oivlkldv","kovdlliv","vdlvokil","ivllokdv","oklvilvd","lovvldik","illvkvod","doviklvl","lvokdliv","kivvldlo","lkvivldo","dillvovk","dllkoivv","klivlvdo","dkvliolv","kvllvoid","oklidvlv","kdivllvo","dvlvloki","ldlivokv","dvkllvio","iovldlkv","koivldlv","vldlkoiv","ovlidklv","lokdivlv","olvvikdl","oklvildv","vkdvloli","iklvodvl","kidvlvlo","vliokldv","vdokllvi","okilvvdl","oikvvdll","llkovvdi","okvildvl","lvilvkdo","dlkvvoil","vodikvll","lvlvdkoi","vkdlvlio","lkldvoiv","lodlvvki","dovkllvi","vkvldiol","kivlvodl","dvlkoilv","lvkodlvi","dlklvivo","oilvvldk","vklvodil","lkldvovi","dvliokvl","loilkvdv","kdvloivl","illovdkv","dkliolvv","vlldokvi","kolvvlid","kllvdvoi","vikvldol","ovidlvlk","lvvoidkl","klivldvo","odlvkivl","iloldkvv","lolkvvid","lklvidvo","divvkllo","oilvdlvk","vdvliokl","kovivldl","lvviodlk","lvkdvloi","olidkvlv","vidvolkl","ilvdokvl","lvldkoiv","vlkvildo","kvvodlil","vliokdlv","lvdvlkio","kdvilolv","ikvodvll","llkivdov","vkldivol","vlkvlido","ldlvkvoi","kvodlvli","kvilldov","vlioklvd","dvllvoik","klidvlvo","koldvvil","odkvillv","liovlkvd","vdkilovl","ldikvvol","lvivkldo","ivkovdll","livlkovd","klovvdil","kilolvdv","ldkvolvi","lvkodliv","olvlikvd","vlvolkdi","vlkoivdl","lvdlvkoi","vodklvli","illkvvod","idoklvvl","lodvkilv","dvlvkoil","ovdillkv","ilkvodvl","dovvlkli","lovdvlki","illvdvok","llkidvvo","kovdvill","lildovkv","ivlovlkd","ivolvlkd","viovdkll","lvdkilov","vlliovdk","vdvolikl","lvviolkd","lilkdovv","lvolkdiv","ikdlvovl","kvvolild","kvliovdl","lvlodvik","diovlvkl","oilvlvkd","lodkvvil","dvollvik","odkillvv","ldklvoiv","ivklvdlo","dovklivl","vldoilvk","livokldv","ldkvlovi","llikovvd","kvlloidv","vilklvod","ldviolkv","klvidlvo","kovdlivl","ovdllikv","illovkdv","odvvllki","ilvvokdl","lvlivdko","idvllovk","vollvidk","iovvkdll","lilvovkd","dollvvik","vdkvoill","lvkidvlo","lvdvilok","vdovkill","vvolilkd","ldlivkvo","vdlviklo","ldoilvvk","klvviold","odlvvkil","vlolkivd","lovkilvd","volildkv","dvollivk","lvkloivd","llovdvik","kldvilvo","ivloklvd","ldkvviol","okdvlilv","villdvko","lidlvkov","vkdilvol","ovillkdv","ikvolvdl","vlokdvli","likvdlvo","dlvolivk","kvlvodli","kdvolivl","lkiovvdl","loilvvdk","ovlvkild","dlkvoivl","ldovvlki","vviloldk","dvlklvio","kidlvlov","idlvkovl","ilvdklov","dvvlkiol","vdvilkol","vlviolkd","vlidvolk","dklvviol","lvikdlov","okvlvldi","kivllvod","vldvoikl","kvovlild","ovvdikll","kdivvlol","dlkiovlv","lviodvkl","vvdklilo","lviokdvl","kdlvoilv","dolvvkli","kdvloilv","lkdolvvi","kdovilvl","dioklvvl","ldvovlki","dlvkvlio","dvoivlkl","dvvolilk","ldlivkov","vlkidlov","lilkovdv","vdvliklo","ilvklovd","lvokidlv","dilvvlko","ivllvodk","dilvovlk","koilvldv","kvdolvli","kldvviol","ildkvlov","ovlidkvl","vlvokild"};
        System.out.println(s1.numSimilarGroups(A));
    }
}
