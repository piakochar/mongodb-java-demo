package demo;

import demo.dao.GenericDao;
import demo.dao.ReviewsDao;
import demo.dao.ViewsDao;
import demo.model.Review;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ApplicationController {

    @GetMapping("/data")
    public String greeting(
            @RequestParam(name="db", required=false, defaultValue="siteData") String dbName,
            @RequestParam(name="collection", required=false, defaultValue="views") String collName,
            Model model) {

        ViewsDao.saveCollectionView(new Date(), dbName, collName);
        final GenericDao dao = new GenericDao(dbName, collName);

        model.addAttribute("dbName", dbName);
        model.addAttribute("collName", collName);
        model.addAttribute("count", dao.countDocuments());
        model.addAttribute("review", new Review());

        return "data";
    }

    @PostMapping("/data")
    public RedirectView submitReview(@ModelAttribute Review review) {
        review.setDate(new Date());
        ReviewsDao.saveReview(review);
        return new RedirectView("/reviews");
    }

    @GetMapping("/views")
    public String userViews(Model model) {

        final Map<String, Integer> viewsByNamespace = ViewsDao.getViewsByNamespace();
        model.addAttribute("viewsByNamespace", viewsByNamespace);

        return "views";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {

        model.addAttribute("reviews", ReviewsDao.findReviews(10));
        return "reviews";
    }
}
