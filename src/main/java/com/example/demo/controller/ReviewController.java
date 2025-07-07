package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ReviewRegistForm;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReviewController {

	private final RegistService service;

	//下記、Spring Bootのソース>フィールドを使用してコンストラクターを生成　により自動生成。
//	(デフォルト・コンストラクターsupe()の呼び出しは、省略にチェック)
//	@Autowired これは、コンストラクターが一つのため省略が可能
	//下記は、lombokの@RequiredArgsConstructorをつけると、初期化が必要なフィールドを
	//初期化するコンストラクタを自動生成するので、省略が可能。
//	public ReviewController(RegistService service) {
//		this.service = service;
//	}

	//	レビュー登録画面表示リクエスト
	@GetMapping("/show-review-form")
	public String showReviewForm(@ModelAttribute ReviewRegistForm form) {
		return "regist-review";
	}

	//	レビュー登録画面リクエスト　戻るボタン押下後
	@PostMapping("/show-review-form-ret")
	public String showReviewFormRet(@ModelAttribute ReviewRegistForm form) {
		return "regist-review";
	}

	//	レビュー登録リクエスト
	@PostMapping("/regist-review")
	public String registReview(
			@Validated @ModelAttribute ReviewRegistForm form,
			BindingResult result) {

		// 入力エラーがある場合には、レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "regist-review";
		}

		// validataionエラーがない場合は、レビュー登録確認画面に遷移する
		return "confirm-regist-review";
	}

	//	レビュー登録登録リクエスト 登録確認画面より
	@PostMapping("/confirm-regist-review")
	public String confirmRegistReview(
			@Validated ReviewRegistForm form,
			BindingResult result,
			Model model) {

		// エラーがある場合は、登録画面に戻す	
		if (result.hasErrors()) {
			return "regist-review";
		}

		//		RegistServiceというインターフェースの型で、serviceという変数を定義
		//		RegistService service = new RegistServiceImpl();

		//		RegistServiceMockのほうのオブジェクトを使用。インターフェースの型を使用しているため、型の変更は不要.
		//		インスタンスするメソッドだけ修正
		//		RegistService service = new RegistServiceMock();

		String msg = service.regist();

		model.addAttribute("msg", msg);
		return "complete-regist-review";
	}

}
