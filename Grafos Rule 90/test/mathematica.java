
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rolando
 */
public class mathematica {
    public static void main(String[] args) {
        int[] equiv = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,15,18,19,22,23,24,25,26,27,28,29,30,32,33,34,35,36,37,38,40,41,42,43,4,45,46,50,51,54,56,57,58,60,72,73,74,76,77,78,90,104,105,106,108,128,130,132,133,134,136,137,138,140,142,146,150,152,156,160,161,162,164,170,172,178,184,200,204,232};
        for(int e = 0;e < 83;e++){
            String sentence = "Export[\"R:\\\\Users\\\\Rolando\\\\Dropbox\\\\ESCOM\\\\7mo Semestre\\\\Computing Selected Topics\\\\Reporte 2 - Elemental Cellular Automata\\\\img\\\\"+equiv[e]+".png\", Import[\"R:\\\\Users\\\\Rolando\\\\Documents\\\\NetBeansProjects\\\\RuleAnalyzer\\\\output\\\\"+equiv[e]+"\\\\15.txt\", \"Package\"]]";
            String latex = "\\begin{figure}[p]\n" +
            "\\centering\\includegraphics[width=\\linewidth]{15/"+equiv[e]+"}\n" +
            "\\caption{Regla "+equiv[e]+"}\n" +
            "\\end{figure}\n\\clearpage";
            System.out.println(sentence);
        }
        
    }
}
