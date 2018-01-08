<script type="text/x-template" id="header">
    <nav>
        <div class="nav-wrapper">
            <a href="#" class="brand-logo left">{{headerTitle}}</a>
            <ul id="nav-mobile" class="right">
                <slot>
                    No links provided
                </slot>
            </ul>
        </div>
    </nav>
</script>

<script>
    Vue.component('header-navbar', {
        props: [
            'title',
            'links'
        ],
        data: function () {
            return {
                linksCustomer: [
                    {href: "google.com", title: "gugl"},
                    {href: "google.com", title: "gugle"},
                    {href: "google.com", title: "guglo"}
                ],
                linksAdmin: [
                    {href: "google.com", title: "gugl"},
                    {href: "google.com", title: "gugle"},
                    {href: "google.com", title: "guglo"}
                ]
            }
        },
        computed: {
            isAdmin: function () {
                return this.role == 1
            },

            headerTitle: function () {
                return this.isAdmin ? "Admin panel" : "Control panel";
            }
        },
        template: "#header"
    })
</script>
