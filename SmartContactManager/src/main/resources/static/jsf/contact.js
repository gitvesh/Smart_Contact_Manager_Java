const view=document.getElementById('view_contact_modal')


const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModel=new Modal(view,options,instanceOptions)

  window.showview = function () {
        contactModel.show();
        console.log("sb to thik hai!!!")
    };
           console.log("function ki mkc !!")
   document.addEventListener('DOMContentLoaded', function () {
        const drawerTrigger = document.querySelector('[data-drawer-target="drawer-navigation"]');
        const drawer = document.getElementById('drawer-navigation');
        const closeButton = drawer.querySelector('[data-drawer-hide="drawer-navigation"]');

        drawerTrigger.addEventListener('click', () => {
            drawer.classList.remove('-translate-x-full');
        });

        closeButton.addEventListener('click', () => {
            drawer.classList.add('-translate-x-full');
        });
    });