
import UIKit

open class BaseViewController : UIViewController {
    
    
    open override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    open override func didReceiveMemoryWarning() {
        
    }
    
    
    open func finish() {
        self.presentingViewController?.dismiss(animated:true)
    }
//    override func willMove(toParentViewController parent: UIViewController!) {
//        print("\(self) \(#function) \(parent)")
//    }
//    override func didMove(toParentViewController parent: UIViewController!) {
//        print("\(self) \(#function) \(parent)")
//    }
//    override func viewWillAppear(_ animated: Bool) {
//        super.viewWillAppear(animated)
//        print("\(self) \(#function)")
//    }
//    override func viewDidAppear(_ animated: Bool) {
//        super.viewDidAppear(animated)
//        print("\(self) \(#function)")
//    }
//    override func viewWillDisappear(_ animated: Bool) {
//        super.viewWillDisappear(animated)
//        print("\(self) \(#function)")
//    }
//    override func viewDidDisappear(_ animated: Bool) {
//        super.viewDidDisappear(animated)
//        print("\(self) \(#function)")
//    }
//    override func viewWillLayoutSubviews() {
//        print("\(self) \(#function)")
//    }
//    override func viewDidLayoutSubviews() {
//        print("\(self) \(#function)")
//    }
//    override func updateViewConstraints() {
//        super.updateViewConstraints()
//        print("\(self) \(#function)")
//    }
//    override func viewWillTransition(to size: CGSize, with coordinator: UIViewControllerTransitionCoordinator) {
//        super.viewWillTransition(to: size, with: coordinator)
//        print("\(self) \(#function)")
//    }
//    override func willTransition(to newCollection: UITraitCollection, with coordinator: UIViewControllerTransitionCoordinator) {
//        super.willTransition(to: newCollection, with: coordinator)
//        print("\(self) \(#function)")
//    }
//    override func traitCollectionDidChange(_ previousTraitCollection: UITraitCollection?) {
//        print("\(self) \(#function)")
//    }
}

