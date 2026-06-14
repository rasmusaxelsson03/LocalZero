package controller;

import iterator.EcoActionIterator;
import jakarta.servlet.http.HttpSession;
import mediator.UserService;
import model.EcoAction;
import model.EcoActionType;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.EcoActionRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TrackerController {

    private EcoActionRepository ecoActionRepository;
    private UserService userService;

    public TrackerController(EcoActionRepository ecoActionRepository, UserService userService) {
        this.ecoActionRepository = ecoActionRepository;
        this.userService = userService;
    }

    @GetMapping("/tracker")
    public String showTracker(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        List<EcoAction> allActions = ecoActionRepository.findByUser_Id(userId);
        EcoActionIterator allIterator = new EcoActionIterator(allActions);
        double totalCo2 = allIterator.totalSavings();
        long totalActions = ecoActionRepository.countByUser_Id(userId);

        List<EcoAction> recent = ecoActionRepository.findTop10ByUser_IdOrderByTimestampDesc(userId);
        EcoActionIterator recentIterator = new EcoActionIterator(recent);

        List<Map<String, String>> recentLogs = new ArrayList<>();
        while (recentIterator.hasNext()) {
            EcoAction a = recentIterator.next();
            Map<String, String> map = new HashMap<>();
            map.put("name", a.getDescription());
            map.put("icon", "🌱");
            map.put("timestamp", a.getTimestamp().toLocalDate().toString());
            recentLogs.add(map);
        }

        model.addAttribute("ecoActionTypes", EcoActionType.values());
        model.addAttribute("totalCo2", totalCo2);
        model.addAttribute("totalActions", totalActions);
        model.addAttribute("recentLogs", recentLogs);

        return "tracker";
    }

    @PostMapping("/tracker/log")
    public String logAction(@RequestParam String actionId,
                            HttpSession session,
                            Model model) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userService.findByID(userId);

        EcoActionType type = EcoActionType.valueOf(actionId);
        EcoAction action = new EcoAction(type.displayName, type.co2Saved, user);
        ecoActionRepository.save(action);

        model.addAttribute("success", "Logged: " + type.icon + " " + type.displayName);
        return "redirect:/tracker";
    }
}